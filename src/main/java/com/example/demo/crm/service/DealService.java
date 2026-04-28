package com.example.demo.crm.service;

import com.example.demo.crm.dto.DealRequestDTO;
import com.example.demo.crm.dto.DealResponseDTO;
import com.example.demo.crm.dto.LostSaleAnalysisReportDTO;
import com.example.demo.crm.entity.Customer;
import com.example.demo.crm.entity.Deal;
import com.example.demo.crm.entity.LostReason;
import com.example.demo.crm.entity.User;
import com.example.demo.crm.enums.DealStatus;
import com.example.demo.crm.mapper.DealMapper;
import com.example.demo.crm.repository.CustomerRepository;
import com.example.demo.crm.repository.DealRepository;
import com.example.demo.crm.repository.LostReasonRepository;
import com.example.demo.crm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final LostReasonRepository lostReasonRepository;
    private final DealMapper dealMapper;

    public List<DealResponseDTO> getAll() {
        return dealRepository.findAll().stream()
                .map(dealMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public DealResponseDTO create(DealRequestDTO dto) {
        Deal deal = dealMapper.toEntity(dto);

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Khách hàng với ID: " + dto.getCustomerId()));
        deal.setCustomer(customer);

        if (dto.getAssignedEmployeeId() != null) {
            User employee = userRepository.findById(dto.getAssignedEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Nhân viên với ID: " + dto.getAssignedEmployeeId()));
            deal.setAssignedEmployee(employee);
        }

        if (dto.getStatus() == DealStatus.LOST) {
            validateLostReason(dto.getLostReasonId());
            LostReason reason = lostReasonRepository.findById(dto.getLostReasonId()).orElse(null);
            deal.setLostReason(reason);
        }

        return dealMapper.toDto(dealRepository.save(deal));
    }

    @Transactional
    public DealResponseDTO updateStatus(Long id, DealStatus newStatus, Long lostReasonId) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Cơ hội bán hàng với ID: " + id));

        if (newStatus == DealStatus.LOST) {
            validateLostReason(lostReasonId);
            LostReason reason = lostReasonRepository.findById(lostReasonId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Lý do thất bại"));
            deal.setLostReason(reason);
        } else {
            deal.setLostReason(null);
        }

        // Nếu WON, đảm bảo đã có thông tin chi tiết dịch vụ
        if (newStatus == DealStatus.WON) {
            if (deal.getContractValue() == null || deal.getServiceCategory() == null) {
                throw new RuntimeException("Cần cập nhật giá trị hợp đồng và danh mục dịch vụ trước khi WON");
            }
        }

        deal.setStatus(newStatus);
        return dealMapper.toDto(dealRepository.save(deal));
    }

    public LostSaleAnalysisReportDTO getLostSaleAnalysisReport() {
        LostSaleAnalysisReportDTO report = new LostSaleAnalysisReportDTO();
        report.setTotalLostDeals(dealRepository.countByStatus(DealStatus.LOST));
        report.setLostByEmployee(dealRepository.countLostDealsByEmployee());
        report.setLostByCustomerNeed(dealRepository.countLostDealsByCustomerNeed());
        report.setLostByReason(dealRepository.countLostDealsByReason());
        return report;
    }

    private void validateLostReason(Long lostReasonId) {
        if (lostReasonId == null) {
            throw new RuntimeException("Bắt buộc phải cập nhật Lý do thất bại theo danh mục chuẩn hóa khi chuyển trạng thái LOST");
        }
    }
}
