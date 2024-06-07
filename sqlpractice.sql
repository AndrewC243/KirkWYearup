SELECT * FROM customers WHERE Country = 'UK';
SELECT ProductName,UnitPrice FROM products WHERE UnitPrice > 30;
SELECT COUNT(*) FROM products WHERE Discontinued = TRUE;
SELECT AVG(UnitPrice),MAX(UnitPrice),MIN(UnitPrice) FROM products;
SELECT COUNT(*),CategoryName FROM products JOIN categories ON products.CategoryID = categories.CategoryID GROUP BY products.CategoryID;
SELECT SupplierID,CompanyName FROM suppliers WHERE Country NOT IN ('USA', 'Germany', 'UK');
SELECT DISTINCT Country FROM customers;
SELECT ProductName,UnitPrice FROM products ORDER BY UnitPrice DESC LIMIT 5;
SELECT OrderID,(UnitPrice*Quantity) as Revenue FROM `order details`;
SELECT FirstName,LastName,COUNT(*) FROM orders JOIN employees ON orders.EmployeeID = employees.EmployeeID GROUP BY orders.EmployeeID;

SELECT customers.CustomerID,CompanyName FROM customers
JOIN orders ON orders.CustomerID = customers.CustomerID
GROUP BY customers.CustomerID HAVING COUNT(orders.CustomerID) > 10;

SELECT ProductName FROM products WHERE UnitsInStock = 0;
SELECT ProductName,CategoryName FROM products JOIN categories ON products.CategoryID = categories.CategoryID WHERE CategoryName = 'Beverages' OR CategoryName = 'Confections';

# thank god for chat gpt
SELECT suppliers.SupplierID,CompanyName FROM suppliers 
JOIN products ON products.SupplierID = suppliers.SupplierID
GROUP BY suppliers.SupplierID
HAVING COUNT(suppliers.SupplierID) = (
	SELECT MAX(ProdCount) FROM (SELECT COUNT(SupplierID) AS ProdCount FROM products GROUP BY SupplierID) AS Counts
);

SELECT ProductName,products.ProductID,`order details`.OrderID FROM products
LEFT JOIN `order details` ON `order details`.ProductID = products.ProductID
WHERE `order details`.OrderID IS NULL;

SELECT OrderID FROM orders WHERE OrderDate BETWEEN '1997-12-01' AND '1997-12-31';

SELECT FirstName,LastName FROM employees
JOIN orders ON orders.EmployeeID = employees.EmployeeID
WHERE YEAR(orders.OrderDate) = 1998
GROUP BY employees.EmployeeID
HAVING COUNT(employees.EmployeeID) = (
	SELECT MAX(EmpCount) FROM (SELECT COUNT(EmployeeID) AS EmpCount FROM orders WHERE YEAR(OrderDate) = 1998 GROUP BY EmployeeID) AS ThrowawayName
);

