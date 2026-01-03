-- ============================================
-- HR MANAGEMENT SYSTEM - DATABASE SCHEMA
-- Fully Normalized (3NF) and Optimized
-- ============================================

-- ============================================
-- 1. USER MANAGEMENT & AUTHENTICATION
-- ============================================

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL,
    INDEX idx_email (email),
    INDEX idx_role (role_id),
    INDEX idx_active (is_active)
);

CREATE TABLE roles (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- 2. EMPLOYEE INFORMATION
-- ============================================

CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE NOT NULL,
    employee_code VARCHAR(20) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender ENUM('Male', 'Female', 'Other') NOT NULL,
    marital_status ENUM('Single', 'Married', 'Divorced', 'Widowed'),
    blood_group VARCHAR(5),
    profile_picture_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_employee_code (employee_code),
    INDEX idx_name (first_name, last_name)
);

CREATE TABLE employee_contact_info (
    contact_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT UNIQUE NOT NULL,
    personal_email VARCHAR(255),
    phone_primary VARCHAR(20) NOT NULL,
    phone_secondary VARCHAR(20),
    emergency_contact_name VARCHAR(100),
    emergency_contact_phone VARCHAR(20),
    emergency_contact_relation VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    INDEX idx_phone (phone_primary)
);

CREATE TABLE employee_addresses (
    address_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    address_type ENUM('Current', 'Permanent') NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    is_primary BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    INDEX idx_employee (employee_id),
    INDEX idx_type (address_type)
);

CREATE TABLE employee_bank_details (
    bank_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT UNIQUE NOT NULL,
    bank_name VARCHAR(200) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    ifsc_code VARCHAR(11) NOT NULL,
    pan_number VARCHAR(10) NOT NULL,
    uan_number VARCHAR(12),
    account_holder_name VARCHAR(200) NOT NULL,
    branch_name VARCHAR(200),
    is_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    INDEX idx_employee (employee_id),
    INDEX idx_pan (pan_number),
    INDEX idx_uan (uan_number)
);

-- ============================================
-- 3. COMPANY INFORMATION
-- ============================================

CREATE TABLE company_details (
    company_id INT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(255) NOT NULL,
    company_location VARCHAR(255) NOT NULL,
    logo_url VARCHAR(500),
    registration_number VARCHAR(100),
    tax_id VARCHAR(50),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(255),
    website VARCHAR(255),
    established_date DATE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_active (is_active)
);

-- ============================================
-- 4. JOB & DEPARTMENT INFORMATION
-- ============================================

CREATE TABLE departments (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) UNIQUE NOT NULL,
    department_code VARCHAR(20) UNIQUE NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_code (department_code)
);

CREATE TABLE designations (
    designation_id INT PRIMARY KEY AUTO_INCREMENT,
    designation_name VARCHAR(100) UNIQUE NOT NULL,
    designation_level INT NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_level (designation_level)
);

CREATE TABLE employee_job_details (
    job_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT UNIQUE NOT NULL,
    department_id INT NOT NULL,
    designation_id INT NOT NULL,
    reporting_manager_id INT,
    employment_type ENUM('Full-Time', 'Part-Time', 'Contract', 'Intern') NOT NULL,
    date_of_joining DATE NOT NULL,
    date_of_leaving DATE,
    probation_end_date DATE,
    work_location VARCHAR(100),
    employee_status ENUM('Active', 'On Leave', 'Terminated', 'Resigned') DEFAULT 'Active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (designation_id) REFERENCES designations(designation_id),
    FOREIGN KEY (reporting_manager_id) REFERENCES employees(employee_id),
    INDEX idx_department (department_id),
    INDEX idx_designation (designation_id),
    INDEX idx_manager (reporting_manager_id),
    INDEX idx_status (employee_status)
);

-- ============================================
-- 5. DOCUMENTS MANAGEMENT
-- ============================================

CREATE TABLE document_types (
    document_type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    is_mandatory BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE employee_documents (
    document_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    document_type_id INT NOT NULL,
    document_name VARCHAR(255) NOT NULL,
    document_url VARCHAR(500) NOT NULL,
    document_number VARCHAR(100),
    issue_date DATE,
    expiry_date DATE,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    uploaded_by INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (document_type_id) REFERENCES document_types(document_type_id),
    FOREIGN KEY (uploaded_by) REFERENCES users(user_id),
    INDEX idx_employee (employee_id),
    INDEX idx_type (document_type_id),
    INDEX idx_expiry (expiry_date)
);

-- ============================================
-- 6. SALARY & PAYROLL MANAGEMENT
-- ============================================

CREATE TABLE salary_components (
    component_id INT PRIMARY KEY AUTO_INCREMENT,
    component_name VARCHAR(100) UNIQUE NOT NULL,
    component_type ENUM('Earning', 'Deduction') NOT NULL,
    is_taxable BOOLEAN DEFAULT TRUE,
    calculation_type ENUM('Fixed', 'Percentage') NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_type (component_type)
);

CREATE TABLE employee_salary_structure (
    salary_structure_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    effective_from DATE NOT NULL,
    effective_to DATE,
    ctc_annual DECIMAL(12, 2) NOT NULL,
    basic_salary DECIMAL(12, 2) NOT NULL,
    is_current BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (created_by) REFERENCES users(user_id),
    INDEX idx_employee (employee_id),
    INDEX idx_current (is_current),
    INDEX idx_effective (effective_from, effective_to)
);

CREATE TABLE salary_structure_details (
    detail_id INT PRIMARY KEY AUTO_INCREMENT,
    salary_structure_id INT NOT NULL,
    component_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    percentage DECIMAL(5, 2),
    FOREIGN KEY (salary_structure_id) REFERENCES employee_salary_structure(salary_structure_id) ON DELETE CASCADE,
    FOREIGN KEY (component_id) REFERENCES salary_components(component_id),
    INDEX idx_structure (salary_structure_id),
    INDEX idx_component (component_id)
);

CREATE TABLE payroll (
    payroll_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    salary_structure_id INT NOT NULL,
    pay_period_month INT NOT NULL,
    pay_period_year INT NOT NULL,
    gross_salary DECIMAL(12, 2) NOT NULL,
    total_deductions DECIMAL(12, 2) NOT NULL,
    net_salary DECIMAL(12, 2) NOT NULL,
    days_worked DECIMAL(5, 2) NOT NULL,
    days_absent DECIMAL(5, 2) DEFAULT 0,
    days_on_leave DECIMAL(5, 2) DEFAULT 0,
    payment_date DATE,
    payment_status ENUM('Pending', 'Processed', 'Paid', 'On Hold') DEFAULT 'Pending',
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed_by INT,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (salary_structure_id) REFERENCES employee_salary_structure(salary_structure_id),
    FOREIGN KEY (processed_by) REFERENCES users(user_id),
    INDEX idx_employee (employee_id),
    INDEX idx_period (pay_period_year, pay_period_month),
    INDEX idx_status (payment_status),
    UNIQUE KEY unique_employee_period (employee_id, pay_period_year, pay_period_month)
);

CREATE TABLE payroll_details (
    payroll_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    payroll_id INT NOT NULL,
    component_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (payroll_id) REFERENCES payroll(payroll_id) ON DELETE CASCADE,
    FOREIGN KEY (component_id) REFERENCES salary_components(component_id),
    INDEX idx_payroll (payroll_id)
);

-- ============================================
-- 6. ATTENDANCE MANAGEMENT
-- ============================================

CREATE TABLE attendance (
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    attendance_date DATE NOT NULL,
    check_in_time TIMESTAMP NULL,
    check_out_time TIMESTAMP NULL,
    total_hours DECIMAL(5, 2),
    attendance_status ENUM('Present', 'Absent', 'Half-Day', 'Leave') NOT NULL,
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    INDEX idx_employee (employee_id),
    INDEX idx_date (attendance_date),
    INDEX idx_status (attendance_status),
    UNIQUE KEY unique_employee_date (employee_id, attendance_date)
);

-- ============================================
-- 7. LEAVE MANAGEMENT
-- ============================================

CREATE TABLE leave_types (
    leave_type_id INT PRIMARY KEY AUTO_INCREMENT,
    leave_type_name VARCHAR(100) UNIQUE NOT NULL,
    leave_category ENUM('Paid', 'Sick', 'Unpaid') NOT NULL,
    max_days_per_year INT,
    carry_forward_allowed BOOLEAN DEFAULT FALSE,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_category (leave_category)
);

CREATE TABLE employee_leave_balance (
    balance_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    leave_type_id INT NOT NULL,
    year INT NOT NULL,
    total_allocated DECIMAL(5, 2) NOT NULL,
    used_days DECIMAL(5, 2) DEFAULT 0,
    pending_days DECIMAL(5, 2) DEFAULT 0,
    available_days DECIMAL(5, 2) NOT NULL,
    carried_forward DECIMAL(5, 2) DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (leave_type_id) REFERENCES leave_types(leave_type_id),
    INDEX idx_employee (employee_id),
    INDEX idx_year (year),
    UNIQUE KEY unique_employee_leave_year (employee_id, leave_type_id, year)
);

CREATE TABLE leave_requests (
    leave_request_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    leave_type_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_days DECIMAL(5, 2) NOT NULL,
    reason TEXT NOT NULL,
    leave_status ENUM('Pending', 'Approved', 'Rejected') DEFAULT 'Pending',
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    reviewed_at TIMESTAMP NULL,
    reviewed_by INT,
    reviewer_comments TEXT,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (leave_type_id) REFERENCES leave_types(leave_type_id),
    FOREIGN KEY (reviewed_by) REFERENCES users(user_id),
    INDEX idx_employee (employee_id),
    INDEX idx_status (leave_status),
    INDEX idx_dates (start_date, end_date),
    INDEX idx_reviewer (reviewed_by)
);

-- ============================================
-- 8. AUDIT & ACTIVITY LOG
-- ============================================

CREATE TABLE audit_logs (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    action_type VARCHAR(50) NOT NULL,
    table_name VARCHAR(100) NOT NULL,
    record_id INT NOT NULL,
    old_values JSON,
    new_values JSON,
    ip_address VARCHAR(45),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    INDEX idx_user (user_id),
    INDEX idx_table (table_name),
    INDEX idx_created (created_at)
);

-- ============================================
-- 9. INITIAL DATA INSERTION
-- ============================================

-- Insert default roles
INSERT INTO roles (role_name, description) VALUES
('HR', 'Human Resources - Full system access'),
('Employee', 'Employee - Limited access to personal data');

-- Insert default leave types
INSERT INTO leave_types (leave_type_name, leave_category, max_days_per_year, carry_forward_allowed, description) VALUES
('Annual Leave', 'Paid', 20, TRUE, 'Annual paid leave'),
('Sick Leave', 'Sick', 10, FALSE, 'Medical sick leave'),
('Casual Leave', 'Paid', 5, FALSE, 'Casual paid leave'),
('Unpaid Leave', 'Unpaid', NULL, FALSE, 'Leave without pay');

-- Insert default salary components
INSERT INTO salary_components (component_name, component_type, is_taxable, calculation_type, description) VALUES
('Basic Salary', 'Earning', TRUE, 'Fixed', 'Basic salary component'),
('HRA', 'Earning', TRUE, 'Percentage', 'House Rent Allowance'),
('Medical Allowance', 'Earning', TRUE, 'Fixed', 'Medical benefits allowance'),
('Transport Allowance', 'Earning', TRUE, 'Fixed', 'Transport allowance'),
('Provident Fund', 'Deduction', FALSE, 'Percentage', 'Employee PF contribution'),
('Professional Tax', 'Deduction', FALSE, 'Fixed', 'Professional tax'),
('Income Tax', 'Deduction', FALSE, 'Percentage', 'Tax Deducted at Source');

-- Insert default document types
INSERT INTO document_types (type_name, description, is_mandatory) VALUES
('Aadhar Card', 'Government ID proof', TRUE),
('PAN Card', 'Permanent Account Number', TRUE),
('Educational Certificates', 'Academic qualifications', TRUE),
('Experience Letters', 'Previous employment proof', FALSE),
('Passport', 'Passport copy', FALSE),
('Bank Details', 'Bank account information', TRUE),
('Photo', 'Passport size photograph', TRUE);

-- ============================================
-- 10. VIEWS FOR COMMON QUERIES
-- ============================================

-- View: Complete Employee Profile
CREATE VIEW vw_employee_complete_profile AS
SELECT 
    e.employee_id,
    e.employee_code,
    e.first_name,
    e.last_name,
    CONCAT(e.first_name, ' ', e.last_name) AS full_name,
    e.date_of_birth,
    e.gender,
    e.marital_status,
    e.blood_group,
    e.profile_picture_url,
    u.email,
    u.is_active,
    ec.phone_primary,
    ec.phone_secondary,
    ec.personal_email,
    d.department_name,
    des.designation_name,
    ejd.employment_type,
    ejd.date_of_joining,
    ejd.employee_status,
    ejd.work_location,
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name
FROM employees e
JOIN users u ON e.user_id = u.user_id
LEFT JOIN employee_contact_info ec ON e.employee_id = ec.employee_id
LEFT JOIN employee_job_details ejd ON e.employee_id = ejd.employee_id
LEFT JOIN departments d ON ejd.department_id = d.department_id
LEFT JOIN designations des ON ejd.designation_id = des.designation_id
LEFT JOIN employees m ON ejd.reporting_manager_id = m.employee_id;

-- View: Current Salary Structure
CREATE VIEW vw_current_salary_structure AS
SELECT 
    e.employee_id,
    e.employee_code,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    ess.salary_structure_id,
    ess.ctc_annual,
    ess.basic_salary,
    ess.effective_from,
    sc.component_name,
    sc.component_type,
    ssd.amount
FROM employees e
JOIN employee_salary_structure ess ON e.employee_id = ess.employee_id
JOIN salary_structure_details ssd ON ess.salary_structure_id = ssd.salary_structure_id
JOIN salary_components sc ON ssd.component_id = sc.component_id
WHERE ess.is_current = TRUE;

-- View: Leave Balance Summary
CREATE VIEW vw_leave_balance_summary AS
SELECT 
    e.employee_id,
    e.employee_code,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    lt.leave_type_name,
    lt.leave_category,
    elb.year,
    elb.total_allocated,
    elb.used_days,
    elb.pending_days,
    elb.available_days
FROM employees e
JOIN employee_leave_balance elb ON e.employee_id = elb.employee_id
JOIN leave_types lt ON elb.leave_type_id = lt.leave_type_id;

-- View: Pending Leave Requests
CREATE VIEW vw_pending_leave_requests AS
SELECT 
    lr.leave_request_id,
    e.employee_id,
    e.employee_code,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    d.department_name,
    lt.leave_type_name,
    lt.leave_category,
    lr.start_date,
    lr.end_date,
    lr.total_days,
    lr.reason,
    lr.applied_at,
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name
FROM leave_requests lr
JOIN employees e ON lr.employee_id = e.employee_id
JOIN leave_types lt ON lr.leave_type_id = lt.leave_type_id
JOIN employee_job_details ejd ON e.employee_id = ejd.employee_id
JOIN departments d ON ejd.department_id = d.department_id
LEFT JOIN employees m ON ejd.reporting_manager_id = m.employee_id
WHERE lr.leave_status = 'Pending';

-- View: Monthly Attendance Summary
CREATE VIEW vw_monthly_attendance_summary AS
SELECT 
    e.employee_id,
    e.employee_code,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    YEAR(a.attendance_date) AS year,
    MONTH(a.attendance_date) AS month,
    COUNT(CASE WHEN a.attendance_status = 'Present' THEN 1 END) AS present_days,
    COUNT(CASE WHEN a.attendance_status = 'Absent' THEN 1 END) AS absent_days,
    COUNT(CASE WHEN a.attendance_status = 'Half-Day' THEN 1 END) AS half_days,
    COUNT(CASE WHEN a.attendance_status = 'Leave' THEN 1 END) AS leave_days
FROM employees e
LEFT JOIN attendance a ON e.employee_id = a.employee_id
GROUP BY e.employee_id, YEAR(a.attendance_date), MONTH(a.attendance_date);

-- ============================================
-- END OF SCHEMA
-- ============================================
