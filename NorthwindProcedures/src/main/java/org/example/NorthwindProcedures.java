package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class NorthwindProcedures {
    private BasicDataSource dataSource;

    public NorthwindProcedures(String url, String username, String password) {
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    public Object[] customerOrderHistory(String customerId) {
        String call = "{CALL northwind.CustOrderHist(?)}";

        try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(call)) {
            cs.setString(1, customerId);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return new Object[]{rs.getString("ProductName"), rs.getInt("TOTAL")};
                }
            }
        } catch (SQLException ignored) {
        }
        throw new RuntimeException("Failed :((((");
    }

    public Object[] salesByYear(String startDate, String endDate) {
        String call = "{CALL northwind.`Sales by Year`(?,?)}";

        try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(call)) {
            cs.setString(1, startDate);
            cs.setString(2, endDate);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return new Object[]{
                            rs.getString("ShippedDate"),
                            rs.getInt("OrderID"),
                            rs.getDouble("Subtotal"),
                            rs.getString("Year")
                    };
                }
            }
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        throw new RuntimeException("Failed :((((");
    }

    public Object[] salesByCategory(String category, int year) {
        String call = "{CALL northwind.SalesByCategory(?,?)}";

        try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(call)) {
            cs.setString(1, category);
            cs.setInt(2, year);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return new Object[]{
                            rs.getString("ProductName"),
                            rs.getDouble("TotalPurchase"),
                    };
                }
            }
        } catch (SQLException ignored) {}
        throw new RuntimeException("Failed :((((");
    }
}
