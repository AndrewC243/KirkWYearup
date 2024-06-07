CREATE TABLE dealerships(
	dealership_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    address VARCHAR(50),
    phone VARCHAR(12)
);

CREATE TABLE vehicles(
	vin VARCHAR(5) PRIMARY KEY,
    year INT,
    make VARCHAR(50),
    model VARCHAR(50),
    type VARCHAR(20),
    color VARCHAR(12),
    odometer INT,
    price DECIMAL(7,2),
    sold BIT
);

CREATE TABLE inventory(
	dealership_id INT,
    vin VARCHAR(5),
    FOREIGN KEY(dealership_id) REFERENCES dealerships(dealership_id),
    FOREIGN KEY(vin) REFERENCES vehicles(vin)
);

CREATE TABLE sales_contracts(
	sales_contract_id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(5),
    customer_name VARCHAR(50),
    customer_email VARCHAR(50),
    contract_date DATE,
    financed BIT,
    total_price DECIMAL(7,2),
    monthly_payment DECIMAL(7,2),
    FOREIGN KEY(vin) REFERENCES vehicles(vin)
);

INSERT INTO dealerships (name, address, phone) VALUES 
('ABC Motors', '123 Main St, Cityville', '555-1234'),
('XYZ Auto', '456 Oak St, Townsville', '555-5678'),
('123 Car Dealers', '789 Elm St, Villagetown', '555-9101');

INSERT INTO vehicles (vin, year, make, model, type, color, odometer, price, sold) VALUES
('ABC01', 2023, 'Toyota', 'Camry', 'Sedan', 'Blue', 15000, 25000.00, 0),
('XYZ01', 2022, 'Honda', 'Civic', 'Sedan', 'Red', 20000, 22000.00, 0),
('12301', 2024, 'Ford', 'F-150', 'Truck', 'Black', 10000, 35000.00, 1),
('ABC02', 2023, 'Nissan', 'Altima', 'Sedan', 'White', 18000, 27000.00, 0),
('XYZ02', 2022, 'Toyota', 'Rav4', 'SUV', 'Silver', 25000, 30000.00, 1);

INSERT INTO inventory (dealership_id, vin) VALUES
(1, 'ABC01'),
(1, 'XYZ01'),
(2, '12301'),
(3, 'ABC02'),
(3, 'XYZ02');

INSERT INTO inventory(dealership_id, vin)
SELECT 1 AS dealership_id,vin FROM vehicles;

INSERT INTO sales_contracts (vin, customer_name, customer_email, contract_date, financed, total_price, monthly_payment) VALUES
('12301', 'Bob Johnson', 'bob@example.com', '2024-06-03', 1, 27000.00, 500.00),
('XYZ02', 'Alice Brown', 'alice@example.com', '2024-06-04', 0, 30000.00, NULL);
# 1
SELECT * FROM dealerships;
# 2
SELECT * FROM vehicles
JOIN inventory ON inventory.vin = vehicles.vin
WHERE dealership_id = 1;
# 3
SELECT * FROM vehicles WHERE vin = '12301';
# 4
SELECT * FROM vehicles WHERE vin = '12301';
SELECT * FROM dealerships
JOIN inventory ON inventory.dealership_id = dealerships.dealership_id
WHERE inventory.vin = '12301';
# 5
SELECT * FROM dealerships
JOIN inventory ON inventory.dealership_id = dealerships.dealership_id
JOIN vehicles ON vehicles.vin = inventory.vin
WHERE make = 'Toyota';
# 6
SELECT * FROM sales_contracts
JOIN inventory ON inventory.vin = sales_contracts.vin
WHERE inventory.dealership_id = 3 AND contract_date BETWEEN '2024-06-01' AND '2024-06-30';