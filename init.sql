-- Database: crm_demo

-- 1. Table: users
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    status VARCHAR(50)
);

-- 2. Table: customers
CREATE TABLE IF NOT EXISTS customers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    address VARCHAR(255),
    tax_code VARCHAR(255),
    assigned_sales_id BIGINT,
    source VARCHAR(255),
    status VARCHAR(50),
    CONSTRAINT fk_customer_sales FOREIGN KEY (assigned_sales_id) REFERENCES users(id)
);

-- Indexes for customers
CREATE INDEX IF NOT EXISTS idx_customer_phone ON customers(phone);
CREATE INDEX IF NOT EXISTS idx_customer_sales_id ON customers(assigned_sales_id);

-- 3. Table: customer_care_history
CREATE TABLE IF NOT EXISTS customer_care_history (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    type VARCHAR(50) NOT NULL,
    content TEXT,
    created_by_id BIGINT,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_care_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_care_creator FOREIGN KEY (created_by_id) REFERENCES users(id)
);

-- 4. Table: customer_complaints
CREATE TABLE IF NOT EXISTS customer_complaints (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_complaint_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Sample Data (Optional)
-- INSERT INTO users (username, password, role, status) VALUES ('admin', '$2a$10$8.UnVuG9HHgffUDAlk8q6uy5QLKGK.T.n8.y0fX66y165s66f6y', 'ADMIN', 'ACTIVE');
