package com.example.demo.crm.service;

import com.example.demo.crm.dto.ActivityRequestDTO;
import com.example.demo.crm.dto.ActivityResponseDTO;
import com.example.demo.crm.dto.PerformanceReportDTO;
import com.example.demo.crm.entity.Activity;
import com.example.demo.crm.entity.Customer;
import com.example.demo.crm.entity.CustomerCareHistory;
import com.example.demo.crm.entity.User;
import com.example.demo.crm.enums.ActivityStatus;
import com.example.demo.crm.enums.CustomerPriority;
import com.example.demo.crm.mapper.ActivityMapper;
import com.example.demo.crm.repository.ActivityRepository;
import com.example.demo.crm.repository.CustomerCareHistoryRepository;
import com.example.demo.crm.repository.CustomerRepository;
import com.example.demo.crm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final CustomerCareHistoryRepository historyRepository;
    private final ActivityMapper activityMapper;

    public List<ActivityResponseDTO> getMyActivities(Long employeeId) {
        return activityRepository.findByAssignedEmployeeIdOrderByDueDateAsc(employeeId)
                .stream().map(activityMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public ActivityResponseDTO create(ActivityRequestDTO dto) {
        Activity activity = activityMapper.toEntity(dto);
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));
        User employee = userRepository.findById(dto.getAssignedEmployeeId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

        activity.setCustomer(customer);
        activity.setAssignedEmployee(employee);
        activity.setStatus(ActivityStatus.PENDING);

        return activityMapper.toDto(activityRepository.save(activity));
    }

    @Transactional
    public ActivityResponseDTO completeActivity(Long id, String resultNote) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhắc việc"));

        if (activity.getStatus() == ActivityStatus.COMPLETED) {
            throw new RuntimeException("Nhắc việc này đã được hoàn thành trước đó");
        }

        // Cập nhật trạng thái
        activity.setStatus(ActivityStatus.COMPLETED);
        activity.setCompletedAt(LocalDateTime.now());
        activityRepository.save(activity);

        // Tạo lịch sử chăm sóc
        CustomerCareHistory history = new CustomerCareHistory();
        history.setCustomer(activity.getCustomer());
        history.setType(activity.getType());
        history.setContent(resultNote != null ? resultNote : "Hoàn thành nhắc việc tự động");
        history.setCreatedBy(activity.getAssignedEmployee());
        history.setRelatedActivity(activity);
        historyRepository.save(history);

        // Tự động lên lịch nhắc việc tiếp theo dựa trên Customer Priority
        CustomerPriority priority = activity.getCustomer().getPriority();
        if (priority != null) {
            int daysToAdd = 30; // Default COLD
            if (priority == CustomerPriority.HOT) daysToAdd = 3;
            else if (priority == CustomerPriority.WARM) daysToAdd = 7;

            Activity newActivity = new Activity();
            newActivity.setCustomer(activity.getCustomer());
            newActivity.setAssignedEmployee(activity.getAssignedEmployee());
            newActivity.setType(activity.getType());
            newActivity.setDueDate(LocalDateTime.now().plusDays(daysToAdd));
            newActivity.setStatus(ActivityStatus.PENDING);
            newActivity.setDescription("Nhắc việc tự động (" + priority.name() + " định kỳ)");
            activityRepository.save(newActivity);
        }

        return activityMapper.toDto(activity);
    }

    // Cron job chạy mỗi giờ (3600000 ms) để quét các Activity quá hạn
    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void scanOverdueActivities() {
        log.info("Bắt đầu quét nhắc việc quá hạn...");
        List<Activity> overdues = activityRepository.findActivitiesByStatusAndDueDateBefore(ActivityStatus.PENDING, LocalDateTime.now());
        if (!overdues.isEmpty()) {
            for (Activity a : overdues) {
                a.setStatus(ActivityStatus.OVERDUE);
            }
            activityRepository.saveAll(overdues);
            log.warn("Đã cập nhật {} nhắc việc thành OVERDUE!", overdues.size());
        }
    }

    public PerformanceReportDTO getPerformanceReport(LocalDateTime startDate, LocalDateTime endDate) {
        PerformanceReportDTO report = new PerformanceReportDTO();
        report.setFromDate(startDate);
        report.setToDate(endDate);
        report.setPerformanceStats(historyRepository.countInteractionsByEmployee(startDate, endDate));
        return report;
    }
}
