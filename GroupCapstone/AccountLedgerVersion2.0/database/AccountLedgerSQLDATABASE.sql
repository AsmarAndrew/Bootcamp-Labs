-- Create Database
CREATE DATABASE IF NOT EXISTS accounting_ledger;

-- Use the Database
USE accounting_ledger;

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Create Profiles Table
CREATE TABLE IF NOT EXISTS profiles (
    user_id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    address VARCHAR(255),
    city VARCHAR(50),
    state VARCHAR(20),
    zip VARCHAR(10),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Create Transactions Table
CREATE TABLE IF NOT EXISTS transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(255),
    vendor VARCHAR(100),
    amount DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Stored Procedures
DELIMITER //

CREATE PROCEDURE AddDeposit(
    IN p_user_id INT, 
    IN p_description VARCHAR(255), 
    IN p_vendor VARCHAR(100), 
    IN p_amount DECIMAL(10, 2)
)
BEGIN
    INSERT INTO transactions (user_id, description, vendor, amount)
    VALUES (p_user_id, p_description, p_vendor, p_amount);
END //

CREATE PROCEDURE MakePayment(
    IN p_user_id INT, 
    IN p_description VARCHAR(255), 
    IN p_vendor VARCHAR(100), 
    IN p_amount DECIMAL(10, 2)
)
BEGIN
    INSERT INTO transactions (user_id, description, vendor, amount)
    VALUES (p_user_id, p_description, p_vendor, -p_amount);
END //

CREATE PROCEDURE ShowAllLedgers(
    IN p_user_id INT
)
BEGIN
    SELECT * FROM transactions WHERE user_id = p_user_id;
END //

CREATE PROCEDURE ShowAllDeposits(
    IN p_user_id INT
)
BEGIN
    SELECT * FROM transactions WHERE user_id = p_user_id AND amount > 0;
END //

CREATE PROCEDURE ShowAllPayments(
    IN p_user_id INT
)
BEGIN
    SELECT * FROM transactions WHERE user_id = p_user_id AND amount < 0;
END //

CREATE PROCEDURE ShowReportsByMonthToDate(
    IN p_user_id INT
)
BEGIN
    SELECT * FROM transactions
    WHERE user_id = p_user_id
    AND YEAR(date) = YEAR(CURRENT_DATE) 
    AND MONTH(date) = MONTH(CURRENT_DATE);
END //

CREATE PROCEDURE ShowReportsByPreviousMonth(
    IN p_user_id INT
)
BEGIN
    SELECT * FROM transactions
    WHERE user_id = p_user_id
    AND YEAR(date) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH)
    AND MONTH(date) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH);
END //

CREATE PROCEDURE ShowReportsByYearToDate(
    IN p_user_id INT
)
BEGIN
    SELECT * FROM transactions
    WHERE user_id = p_user_id
    AND YEAR(date) = YEAR(CURRENT_DATE);
END //

CREATE PROCEDURE ShowReportsByPreviousYear(
    IN p_user_id INT
)
BEGIN
    SELECT * FROM transactions
    WHERE user_id = p_user_id
    AND YEAR(date) = YEAR(CURRENT_DATE - INTERVAL 1 YEAR);
END //

CREATE PROCEDURE SearchByVendor(
    IN p_user_id INT,
    IN p_vendor VARCHAR(100)
)
BEGIN
    SELECT * FROM transactions WHERE user_id = p_user_id AND vendor = p_vendor;
END //

CREATE PROCEDURE CustomSearch(
    IN p_user_id INT,
    IN p_start_date DATE, 
    IN p_end_date DATE, 
    IN p_vendor VARCHAR(100)
)
BEGIN
    SELECT * FROM transactions 
    WHERE user_id = p_user_id
    AND date BETWEEN p_start_date AND p_end_date 
    AND vendor = p_vendor;
END //

-- Create procedure to view profile
CREATE PROCEDURE ViewProfile(
    IN p_user_id INT
)
BEGIN
    SELECT * FROM profiles WHERE user_id = p_user_id;
END //

CREATE PROCEDURE UpdateProfile(
    IN p_user_id INT,
    IN p_first_name VARCHAR(50),
    IN p_last_name VARCHAR(50),
    IN p_phone VARCHAR(20),
    IN p_email VARCHAR(100),
    IN p_address VARCHAR(255),
    IN p_city VARCHAR(50),
    IN p_state VARCHAR(20),
    IN p_zip VARCHAR(10)
)
BEGIN
    UPDATE profiles
    SET first_name = p_first_name,
        last_name = p_last_name,
        phone = p_phone,
        email = p_email,
        address = p_address,
        city = p_city,
        state = p_state,
        zip = p_zip
    WHERE user_id = p_user_id;
END //

DELIMITER ;

-- Sample Data
INSERT INTO users (username, password) VALUES ('admin', 'adminpassword');
INSERT INTO users (username, password) VALUES ('user1', 'user1password');
INSERT INTO users (username, password) VALUES ('user2', 'user2password');

INSERT INTO profiles (user_id, first_name, last_name, phone, email, address, city, state, zip)
VALUES (1, 'Admin', 'User', '123-456-7890', 'admin@example.com', '123 Admin St', 'Admin City', 'Admin State', '12345');

INSERT INTO profiles (user_id, first_name, last_name, phone, email, address, city, state, zip)
VALUES (2, 'John', 'Doe', '123-456-7891', 'john.doe@example.com', '456 John St', 'Doe City', 'Doe State', '67890');

INSERT INTO profiles (user_id, first_name, last_name, phone, email, address, city, state, zip)
VALUES (3, 'Jane', 'Smith', '123-456-7892', 'jane.smith@example.com', '789 Jane St', 'Smith City', 'Smith State', '11223');

-- Perform operations
CALL AddDeposit(2, 'Salary Deposit', 'Company', 1000.00);
CALL MakePayment(2, 'Grocery Shopping', 'Supermarket', 150.00);
CALL AddDeposit(3, 'Freelance Payment', 'Client', 500.00);
CALL MakePayment(3, 'Electric Bill', 'Utility Company', 100.00);
