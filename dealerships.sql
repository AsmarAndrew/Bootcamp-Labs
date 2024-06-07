/*
	Create DataBase called Dealerships
*/

CREATE database dealerships;


/*
	Create tables dealership, vehicles, inventory, sales contract, lease contract
*/

CREATE TABLE dealership (
    dealership_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    address VARCHAR(50),
    phone VARCHAR(12)
);

CREATE TABLE vehicles (
    vin VARCHAR(20) PRIMARY KEY,
    year INT,
    make VARCHAR(50),
    model VARCHAR(50),
    vehicleType VARCHAR(50),
    color VARCHAR(20),
    odometer DECIMAL(10, 2),
    price DECIMAL(10, 2)
);

CREATE TABLE inventory (
    dealership_id INT,
    vin VARCHAR(20),
    PRIMARY KEY (dealership_id, vin),
    CONSTRAINT fk_dealership
        FOREIGN KEY (dealership_id) REFERENCES dealership(dealership_id),
    CONSTRAINT fk_vehicle
        FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

CREATE TABLE sales_contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(20),
    sale_date DATE,
    customer_name VARCHAR(50),
    customer_address VARCHAR(100),
    customer_phone VARCHAR(15),
    sale_price DECIMAL(10, 2),
    CONSTRAINT fk_vehicle_contract
        FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

CREATE TABLE lease_contracts (
    lease_id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(20),
    lease_start_date DATE,
    lease_end_date DATE,
    customer_name VARCHAR(50),
    customer_address VARCHAR(100),
    customer_phone VARCHAR(15),
    monthly_payment DECIMAL(10, 2),
    total_lease_amount DECIMAL(10, 2),
    CONSTRAINT fk_vehicle_lease
        FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

/*
    Insert sample data
*/

INSERT INTO dealership (name, address, phone) VALUES
('AutoWorld', '123 Main St', '555-1234'),
('CarDepot', '456 Elm St', '555-5678'),
('DriveNow', '789 Oak St', '555-9012'),
('MegaMotors', '234 Birch St', '555-3333'),
('SuperCars', '567 Pine St', '555-4444');

INSERT INTO vehicles (vin, year, make, model, vehicleType, color, odometer, price) VALUES
('1HGCM82633A004352', 2022, 'Toyota', 'Camry', 'Sedan', 'Blue', 5000, 24000.00),
('1HGCM82633A004353', 2023, 'Honda', 'Civic', 'Sedan', 'Red', 3000, 22000.00),
('1HGCM82633A004354', 2021, 'Ford', 'Mustang', 'Coupe', 'Black', 15000, 28000.00),
('1HGCM82633A004355', 2022, 'Tesla', 'Model S', 'Sedan', 'White', 2000, 75000.00),
('1HGCM82633A004356', 2020, 'Chevrolet', 'Malibu', 'Sedan', 'Gray', 25000, 18000.00),
('1HGCM82633A004357', 2022, 'Nissan', 'Altima', 'Sedan', 'Silver', 8000, 23000.00),
('1HGCM82633A004358', 2023, 'BMW', 'X5', 'SUV', 'Blue', 6000, 60000.00),
('1HGCM82633A004359', 2021, 'Audi', 'A4', 'Sedan', 'Black', 12000, 35000.00),
('1HGCM82633A004360', 2022, 'Mercedes', 'C-Class', 'Sedan', 'White', 4000, 55000.00),
('1HGCM82633A004361', 2020, 'Hyundai', 'Sonata', 'Sedan', 'Gray', 30000, 17000.00);

INSERT INTO inventory (dealership_id, vin) VALUES
(1, '1HGCM82633A004352'),
(1, '1HGCM82633A004353'),
(2, '1HGCM82633A004354'),
(2, '1HGCM82633A004355'),
(3, '1HGCM82633A004356'),
(4, '1HGCM82633A004357'),
(4, '1HGCM82633A004358'),
(5, '1HGCM82633A004359'),
(5, '1HGCM82633A004360'),
(1, '1HGCM82633A004361');

INSERT INTO sales_contracts (vin, sale_date, customer_name, customer_address, customer_phone, sale_price) VALUES
('1HGCM82633A004352', '2023-05-01', 'John Doe', '123 Elm St', '555-9876', 24000.00),
('1HGCM82633A004353', '2023-06-15', 'Jane Smith', '456 Oak St', '555-6543', 22000.00),
('1HGCM82633A004356', '2023-02-10', 'Charlie Davis', '678 Cedar St', '555-8765', 18000.00),
('1HGCM82633A004358', '2023-04-20', 'Dana White', '901 Aspen St', '555-7890', 60000.00);

INSERT INTO lease_contracts (vin, lease_start_date, lease_end_date, customer_name, customer_address, customer_phone, monthly_payment, total_lease_amount) VALUES
('1HGCM82633A004354', '2023-01-01', '2025-01-01', 'Alice Johnson', '789 Pine St', '555-3210', 450.00, 10800.00),
('1HGCM82633A004355', '2023-03-01', '2026-03-01', 'Bob Brown', '101 Maple St', '555-4321', 600.00, 21600.00),
('1HGCM82633A004361', '2023-05-01', '2026-05-01', 'Evan Green', '234 Elm St', '555-4321', 400.00, 14400.00),
('1HGCM82633A004357', '2023-06-01', '2025-06-01', 'Fiona Black', '567 Maple St', '555-5432', 350.00, 12600.00);

/*
    Queries to perform the required tasks
*/

/* Get all dealerships */
SELECT * FROM dealership;

/* Find all vehicles for a specific dealership*/
SELECT v.*
FROM vehicles v
JOIN inventory i ON v.vin = i.vin
WHERE i.dealership_id = 2;

/* Find a car by VIN */
SELECT * FROM vehicles
WHERE vin = '1HGCM82633A004352';

/* Find the dealership where a certain car is located by VIN*/
SELECT d.*
FROM dealership d
JOIN inventory i ON d.dealership_id = i.dealership_id
WHERE i.vin = '1HGCM82633A004352';

/* Find all dealerships that have a certain car type*/
SELECT DISTINCT d.*
FROM dealership d
JOIN inventory i ON d.dealership_id = i.dealership_id
JOIN vehicles v ON i.vin = v.vin
WHERE v.vehicleType = 'Sedan';

/* Get all sales information for a specific dealer for a specific date range*/
SELECT sc.*
FROM sales_contracts sc
JOIN inventory i ON sc.vin = i.vin
WHERE i.dealership_id = 1 AND sc.sale_date BETWEEN '2023-01-01' AND '2023-12-31';

