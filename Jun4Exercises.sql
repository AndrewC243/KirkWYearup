SELECT COUNT(*) FROM suppliers;
SELECT SUM(Salary) FROM employees;
SELECT MIN(UnitPrice) FROM products;
SELECT AVG(UnitPrice) FROM products;
SELECT MAX(UnitPrice) FROM products;
SELECT SUM(UnitsInStock) AS ItemsSupplied,SupplierID FROM products GROUP BY SupplierID ORDER BY ItemsSupplied;
SELECT CategoryID,AVG(UnitPrice) FROM products GROUP BY CategoryID;
SELECT SupplierID,COUNT(*) AS NumItems FROM products GROUP BY SupplierID HAVING NumItems >= 5;
SELECT ProductID,ProductName,(UnitPrice * UnitsInStock) AS InventoryValue FROM products GROUP BY ProductID ORDER BY InventoryValue DESC,ProductName;