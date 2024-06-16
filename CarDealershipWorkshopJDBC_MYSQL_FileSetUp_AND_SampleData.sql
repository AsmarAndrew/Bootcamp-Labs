-- Create tables
CREATE TABLE IF NOT EXISTS dealership (
    dealership_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicles (
    vin VARCHAR(20) PRIMARY KEY,
    year INT NOT NULL,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    vehicleType VARCHAR(50) NOT NULL,
    color VARCHAR(50) NOT NULL,
    odometer DOUBLE NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS inventory (
    vin VARCHAR(20),
    dealership_id INT,
    PRIMARY KEY (vin, dealership_id),
    FOREIGN KEY (vin) REFERENCES vehicles(vin),
    FOREIGN KEY (dealership_id) REFERENCES dealership(dealership_id)
);

CREATE TABLE IF NOT EXISTS lease_contract (
    lease_id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(20),
    start_date DATE,
    end_date DATE,
    customer_name VARCHAR(100),
    customer_email VARCHAR(255),
    customer_phone VARCHAR(50),
    monthly_payment DOUBLE,
    total_amount DOUBLE,
    dealership_id INT,
    FOREIGN KEY (vin) REFERENCES vehicles(vin),
    FOREIGN KEY (dealership_id) REFERENCES dealership(dealership_id)
);

CREATE TABLE IF NOT EXISTS sales_contract (
    sales_id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(20),
    contract_date DATE,
    customer_name VARCHAR(100),
    customer_email VARCHAR(255),
    customer_phone VARCHAR(50),
    finance_option BOOLEAN,
    total_amount DOUBLE,
    dealership_id INT,
    FOREIGN KEY (vin) REFERENCES vehicles(vin),
    FOREIGN KEY (dealership_id) REFERENCES dealership(dealership_id)
);

-- Insert sample data into dealership table
INSERT INTO dealership (name, address, phone) VALUES
('Cascade Auto Sales', '1234 Cascade Ave, Seattle, WA', '206-555-1234'),
('Summit Motors', '5678 Summit Blvd, Denver, CO', '303-555-5678');

-- Insert vehicles for Cascade Auto Sales
INSERT INTO vehicles (vin, year, make, model, vehicleType, color, odometer, price) VALUES
('1HGCM82633A123456', 2020, 'Toyota', 'Camry', 'Sedan', 'Red', 15000, 25000),
('1HGCM82633A123457', 2021, 'Honda', 'Civic', 'Sedan', 'Blue', 12000, 22000),
('1HGCM82633A123458', 2019, 'Ford', 'Escape', 'SUV', 'Black', 30000, 28000),
('1HGCM82633A123459', 2022, 'Chevrolet', 'Malibu', 'Sedan', 'White', 10000, 24000),
('1HGCM82633A123460', 2018, 'Nissan', 'Altima', 'Sedan', 'Gray', 40000, 20000),
('1HGCM82633A123461', 2020, 'Hyundai', 'Elantra', 'Sedan', 'Silver', 25000, 21000),
('1HGCM82633A123462', 2019, 'Kia', 'Optima', 'Sedan', 'Black', 23000, 22000),
('1HGCM82633A123463', 2021, 'Mazda', 'CX-5', 'SUV', 'Blue', 15000, 27000),
('1HGCM82633A123464', 2018, 'Subaru', 'Outback', 'SUV', 'Green', 35000, 25000),
('1HGCM82633A123465', 2022, 'Volkswagen', 'Passat', 'Sedan', 'Red', 12000, 26000),
('1HGCM82633A123466', 2020, 'Jeep', 'Wrangler', 'SUV', 'Black', 15000, 30000),
('1HGCM82633A123467', 2019, 'GMC', 'Terrain', 'SUV', 'White', 20000, 28000),
('1HGCM82633A123468', 2021, 'Chevrolet', 'Equinox', 'SUV', 'Gray', 14000, 26000),
('1HGCM82633A123469', 2022, 'Toyota', 'RAV4', 'SUV', 'Blue', 11000, 29000),
('1HGCM82633A123470', 2019, 'Honda', 'Accord', 'Sedan', 'Silver', 25000, 27000);

-- Insert vehicles for Summit Motors
INSERT INTO vehicles (vin, year, make, model, vehicleType, color, odometer, price) VALUES
('2HGCM82633A123471', 2020, 'Toyota', 'Camry', 'Sedan', 'Red', 15000, 25000),
('2HGCM82633A123472', 2021, 'Honda', 'Civic', 'Sedan', 'Blue', 12000, 22000),
('2HGCM82633A123473', 2019, 'Ford', 'Escape', 'SUV', 'Black', 30000, 28000),
('2HGCM82633A123474', 2022, 'Chevrolet', 'Malibu', 'Sedan', 'White', 10000, 24000),
('2HGCM82633A123475', 2018, 'Nissan', 'Altima', 'Sedan', 'Gray', 40000, 20000),
('2HGCM82633A123476', 2020, 'Hyundai', 'Elantra', 'Sedan', 'Silver', 25000, 21000),
('2HGCM82633A123477', 2019, 'Kia', 'Optima', 'Sedan', 'Black', 23000, 22000),
('2HGCM82633A123478', 2021, 'Mazda', 'CX-5', 'SUV', 'Blue', 15000, 27000),
('2HGCM82633A123479', 2018, 'Subaru', 'Outback', 'SUV', 'Green', 35000, 25000),
('2HGCM82633A123480', 2022, 'Volkswagen', 'Passat', 'Sedan', 'Red', 12000, 26000),
('2HGCM82633A123481', 2020, 'Jeep', 'Wrangler', 'SUV', 'Black', 15000, 30000),
('2HGCM82633A123482', 2019, 'GMC', 'Terrain', 'SUV', 'White', 20000, 28000),
('2HGCM82633A123483', 2021, 'Chevrolet', 'Equinox', 'SUV', 'Gray', 14000, 26000),
('2HGCM82633A123484', 2022, 'Toyota', 'RAV4', 'SUV', 'Blue', 11000, 29000),
('2HGCM82633A123485', 2019, 'Honda', 'Accord', 'Sedan', 'Silver', 25000, 27000);

-- Insert inventory for Cascade Auto Sales
INSERT INTO inventory (vin, dealership_id) VALUES
('1HGCM82633A123456', 1),
('1HGCM82633A123457', 1),
('1HGCM82633A123458', 1),
('1HGCM82633A123459', 1),
('1HGCM82633A123460', 1),
('1HGCM82633A123461', 1),
('1HGCM82633A123462', 1),
('1HGCM82633A123463', 1),
('1HGCM82633A123464', 1),
('1HGCM82633A123465', 1),
('1HGCM82633A123466', 1),
('1HGCM82633A123467', 1),
('1HGCM82633A123468', 1),
('1HGCM82633A123469', 1),
('1HGCM82633A123470', 1);

-- Insert inventory for Summit Motors
INSERT INTO inventory (vin, dealership_id) VALUES
('2HGCM82633A123471', 2),
('2HGCM82633A123472', 2),
('2HGCM82633A123473', 2),
('2HGCM82633A123474', 2),
('2HGCM82633A123475', 2),
('2HGCM82633A123476', 2),
('2HGCM82633A123477', 2),
('2HGCM82633A123478', 2),
('2HGCM82633A123479', 2),
('2HGCM82633A123480', 2),
('2HGCM82633A123481', 2),
('2HGCM82633A123482', 2),
('2HGCM82633A123483', 2),
('2HGCM82633A123484', 2),
('2HGCM82633A123485', 2);

-- Insert lease contracts for Cascade Auto Sales
INSERT INTO lease_contract (vin, start_date, end_date, customer_name, customer_email, customer_phone, monthly_payment, total_amount, dealership_id) VALUES
('1HGCM82633A123456', '2023-01-01', '2026-01-01', 'John Doe', 'johndoe@example.com', '555-1234', 300, 10800, 1),
('1HGCM82633A123457', '2023-02-01', '2026-02-01', 'Jane Smith', 'janesmith@example.com', '555-5678', 280, 10080, 1),
('1HGCM82633A123458', '2023-03-01', '2026-03-01', 'Bob Johnson', 'bobjohnson@example.com', '555-9101', 320, 11520, 1),
('1HGCM82633A123459', '2023-04-01', '2026-04-01', 'Alice White', 'alicewhite@example.com', '555-1122', 310, 11160, 1),
('1HGCM82633A123460', '2023-05-01', '2026-05-01', 'Charlie Brown', 'charliebrown@example.com', '555-3344', 290, 10440, 1);

-- Insert lease contracts for Summit Motors
INSERT INTO lease_contract (vin, start_date, end_date, customer_name, customer_email, customer_phone, monthly_payment, total_amount, dealership_id) VALUES
('2HGCM82633A123471', '2023-01-01', '2026-01-01', 'Emily Green', 'emilygreen@example.com', '555-2233', 300, 10800, 2),
('2HGCM82633A123472', '2023-02-01', '2026-02-01', 'David Brown', 'davidbrown@example.com', '555-3344', 280, 10080, 2),
('2HGCM82633A123473', '2023-03-01', '2026-03-01', 'Sophia Blue', 'sophiablue@example.com', '555-4455', 320, 11520, 2),
('2HGCM82633A123474', '2023-04-01', '2026-04-01', 'Liam Red', 'liamred@example.com', '555-5566', 310, 11160, 2),
('2HGCM82633A123475', '2023-05-01', '2026-05-01', 'Mia Yellow', 'miayellow@example.com', '555-6677', 290, 10440, 2);

-- Insert sales contracts for Cascade Auto Sales
INSERT INTO sales_contract (vin, contract_date, customer_name, customer_email, customer_phone, finance_option, total_amount, dealership_id) VALUES
('1HGCM82633A123461', '2023-03-01', 'Alice Johnson', 'alicej@example.com', '555-8765', TRUE, 28000, 1),
('1HGCM82633A123462', '2023-04-01', 'Bob Smith', 'bobsmith@example.com', '555-1236', FALSE, 22000, 1),
('1HGCM82633A123463', '2023-05-01', 'Charlie Brown', 'charlieb@example.com', '555-6543', TRUE, 27000, 1),
('1HGCM82633A123464', '2023-06-01', 'David Wilson', 'davidw@example.com', '555-7890', FALSE, 25000, 1),
('1HGCM82633A123465', '2023-07-01', 'Emily Davis', 'emilyd@example.com', '555-4321', TRUE, 26000, 1);

-- Insert sales contracts for Summit Motors
INSERT INTO sales_contract (vin, contract_date, customer_name, customer_email, customer_phone, finance_option, total_amount, dealership_id) VALUES
('2HGCM82633A123476', '2023-03-01', 'Frank White', 'frankw@example.com', '555-9876', TRUE, 28000, 2),
('2HGCM82633A123477', '2023-04-01', 'Grace Black', 'graceb@example.com', '555-6543', FALSE, 22000, 2),
('2HGCM82633A123478', '2023-05-01', 'Henry Green', 'henryg@example.com', '555-3210', TRUE, 27000, 2),
('2HGCM82633A123479', '2023-06-01', 'Isabella Blue', 'isabellab@example.com', '555-1234', FALSE, 25000, 2),
('2HGCM82633A123480', '2023-07-01', 'Jack Brown', 'jackb@example.com', '555-4321', TRUE, 26000, 2);

-- Create stored procedures
DELIMITER //

CREATE PROCEDURE AddVehicle(
    IN v_vin VARCHAR(20),
    IN v_year INT,
    IN v_make VARCHAR(50),
    IN v_model VARCHAR(50),
    IN v_type VARCHAR(50),
    IN v_color VARCHAR(50),
    IN v_odometer DOUBLE,
    IN v_price DOUBLE,
    IN d_id INT
)
BEGIN
    INSERT INTO vehicles (vin, year, make, model, vehicleType, color, odometer, price)
    VALUES (v_vin, v_year, v_make, v_model, v_type, v_color, v_odometer, v_price);
    
    INSERT INTO inventory (vin, dealership_id)
    VALUES (v_vin, d_id);
END //

CREATE PROCEDURE RemoveVehicle(
    IN v_vin VARCHAR(20),
    IN d_id INT
)
BEGIN
    DELETE FROM inventory
    WHERE vin = v_vin AND dealership_id = d_id;
    
    DELETE FROM vehicles
    WHERE vin = v_vin;
END //

CREATE PROCEDURE AddLeaseContract(
    IN v_vin VARCHAR(20),
    IN startDate DATE,
    IN endDate DATE,
    IN customerName VARCHAR(100),
    IN customerEmail VARCHAR(255),
    IN customerPhone VARCHAR(50),
    IN monthlyPayment DOUBLE,
    IN totalAmount DOUBLE,
    IN d_id INT
)
BEGIN
    INSERT INTO lease_contract (vin, start_date, end_date, customer_name, customer_email, customer_phone, monthly_payment, total_amount, dealership_id)
    VALUES (v_vin, startDate, endDate, customerName, customerEmail, customerPhone, monthlyPayment, totalAmount, d_id);
    
    DELETE FROM inventory
    WHERE vin = v_vin AND dealership_id = d_id;
END //

CREATE PROCEDURE AddSalesContract(
    IN v_vin VARCHAR(20),
    IN contractDate DATE,
    IN customerName VARCHAR(100),
    IN customerEmail VARCHAR(255),
    IN customerPhone VARCHAR(50),
    IN financeOption BOOLEAN,
    IN totalAmount DOUBLE,
    IN d_id INT
)
BEGIN
    INSERT INTO sales_contract (vin, contract_date, customer_name, customer_email, customer_phone, finance_option, total_amount, dealership_id)
    VALUES (v_vin, contractDate, customerName, customerEmail, customerPhone, financeOption, totalAmount, d_id);
    
    DELETE FROM inventory
    WHERE vin = v_vin AND dealership_id = d_id;
END //

CREATE PROCEDURE GetAllVehicles(
    IN d_id INT
)
BEGIN
    SELECT v.*
    FROM vehicles v
    JOIN inventory i ON v.vin = i.vin
    WHERE i.dealership_id = d_id;
END //

CREATE PROCEDURE GetVehicleByVin(
    IN v_vin VARCHAR(20),
    IN d_id INT
)
BEGIN
    SELECT v.*
    FROM vehicles v
    JOIN inventory i ON v.vin = i.vin
    WHERE v.vin = v_vin AND i.dealership_id = d_id;
END //

CREATE PROCEDURE GetVehiclesByPriceRange(
    IN minPrice DOUBLE,
    IN maxPrice DOUBLE,
    IN d_id INT
)
BEGIN
    SELECT v.*
    FROM vehicles v
    JOIN inventory i ON v.vin = i.vin
    WHERE v.price BETWEEN minPrice AND maxPrice AND i.dealership_id = d_id;
END //

CREATE PROCEDURE GetVehiclesByMakeModel(
    IN v_make VARCHAR(50),
    IN v_model VARCHAR(50),
    IN d_id INT
)
BEGIN
    SELECT v.*
    FROM vehicles v
    JOIN inventory i ON v.vin = i.vin
    WHERE v.make = v_make AND v.model = v_model AND i.dealership_id = d_id;
END //

CREATE PROCEDURE GetVehiclesByYearRange(
    IN minYear INT,
    IN maxYear INT,
    IN d_id INT
)
BEGIN
    SELECT v.*
    FROM vehicles v
    JOIN inventory i ON v.vin = i.vin
    WHERE v.year BETWEEN minYear AND maxYear AND i.dealership_id = d_id;
END //

CREATE PROCEDURE GetVehiclesByColor(
    IN v_color VARCHAR(50),
    IN d_id INT
)
BEGIN
    SELECT v.*
    FROM vehicles v
    JOIN inventory i ON v.vin = i.vin
    WHERE v.color = v_color AND i.dealership_id = d_id;
END //

CREATE PROCEDURE GetVehiclesByMileage(
    IN minMileage DOUBLE,
    IN maxMileage DOUBLE,
    IN d_id INT
)
BEGIN
    SELECT v.*
    FROM vehicles v
    JOIN inventory i ON v.vin = i.vin
    WHERE v.odometer BETWEEN minMileage AND maxMileage AND i.dealership_id = d_id;
END //

CREATE PROCEDURE GetVehiclesByType(
    IN v_type VARCHAR(50),
    IN d_id INT
)
BEGIN
    SELECT v.*
    FROM vehicles v
    JOIN inventory i ON v.vin = i.vin
    WHERE v.vehicleType = v_type AND i.dealership_id = d_id;
END //

CREATE PROCEDURE GetAllDealerships()
BEGIN
    SELECT *
    FROM dealership;
END //

DELIMITER ;