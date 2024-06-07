SELECT * FROM customers;
SELECT CompanyName,ContactName FROM customers;
SELECT DISTINCT Country FROM customers;
SELECT * FROM customers WHERE Country = 'UK';
SELECT * FROM orders WHERE Freight BETWEEN 50 AND 100;
SELECT count(*) FROM orders WHERE OrderDate > '1997-01-01' AND (ShipCountry = 'USA' OR ShipCountry = 'Canada');
SELECT * FROM orders WHERE ShipCountry IN ('France', 'Belgium', 'Germany');
SELECT * FROM products WHERE UnitPrice BETWEEN 10 AND 20;
SELECT * FROM suppliers ORDER BY Country;
SELECT * FROM customers ORDER BY ContactName DESC;
# int, varchar, varchar, varchar, varchar, datetime, datetime, varchar, varchar, varchar, varchar, varchar, varchar, int, image, text, int, varchar
SELECT * FROM orders WHERE OrderDate = '1996-07-04';
# 'Vins et alcools Chevalier'
# 'Queso Cabrales'