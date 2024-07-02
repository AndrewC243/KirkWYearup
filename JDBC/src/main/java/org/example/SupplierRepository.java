package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierRepository {
    private BasicDataSource dataSource;

    public SupplierRepository(String url, String user, String password) {
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
    }

    public List<Supplier> getAllSuppliers() {
        String query = "SELECT * FROM suppliers";
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    suppliers.add(getSupplierByResultSet(rs));
                }
            }
        } catch (SQLException ignored) {
        }
        return suppliers;
    }
    public Optional<Supplier> getSupplierById(int supplierId) {
        String query = "SELECT * FROM suppliers WHERE SupplierID = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, supplierId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(getSupplierByResultSet(rs));
                }
            }
        } catch (SQLException ignored) {
        }
        return Optional.empty();
    }

    public Supplier createSupplier(Supplier supplier) {
        String query = "INSERT INTO " +
                "suppliers(CompanyName,ContactName,ContactTitle,Address,City,Region,PostalCode,Country,Phone,Fax,HomePage)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(query)) {
            ps.setString(1, supplier.getCompanyName());
            ps.setString(2, supplier.getContactName());
            ps.setString(3, supplier.getContactTitle());
            ps.setString(4, supplier.getAddress());
            ps.setString(5, supplier.getCity());
            ps.setString(6, supplier.getRegion());
            ps.setString(7, supplier.getPostalCode());
            ps.setString(8, supplier.getCountry());
            ps.setString(9, supplier.getPhone());
            ps.setString(10, supplier.getFax());
            ps.setString(11, supplier.getHomePage());
            ps.executeUpdate();
        } catch (SQLException ignored) {}
        return supplier;
    }

    public Supplier updateSupplier(Supplier supplier) {
        String query = "UPDATE suppliers SET CompanyName=?,ContactName=?,ContactTitle=?,Address=?,City=?,Region=?,PostalCode=?,Country=?,Phone=?,Fax=?,HomePage=? WHERE SupplierID = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(query)) {
            ps.setString(1, supplier.getCompanyName());
            ps.setString(2, supplier.getContactName());
            ps.setString(3, supplier.getContactTitle());
            ps.setString(4, supplier.getAddress());
            ps.setString(5, supplier.getCity());
            ps.setString(6, supplier.getRegion());
            ps.setString(7, supplier.getPostalCode());
            ps.setString(8, supplier.getCountry());
            ps.setString(9, supplier.getPhone());
            ps.setString(10, supplier.getFax());
            ps.setString(11, supplier.getHomePage());
            ps.setInt(12, supplier.getSupplierId());
            ps.executeUpdate();
        } catch (SQLException ignored) {}
        return supplier;
    }

    public void deleteSupplier(int supplierId) {
        String query = "DELETE FROM suppliers WHERE SupplierID = ?";

        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(query)) {
            ps.setInt(1, supplierId);
            ps.executeUpdate();
        } catch (SQLException ignored) {}
    }

    private Supplier getSupplierByResultSet(ResultSet rs) throws SQLException {
        return Supplier.builder()
                .SupplierId(rs.getInt("SupplierID"))
                .companyName(rs.getString("CompanyName"))
                .contactName(rs.getString("ContactName"))
                .contactTitle(rs.getString("ContactTitle"))
                .address(rs.getString("Address"))
                .city(rs.getString("City"))
                .region(rs.getString("Region"))
                .postalCode(rs.getString("PostalCode"))
                .country(rs.getString("Country"))
                .phone(rs.getString("Phone"))
                .fax(rs.getString("Fax"))
                .homePage(rs.getString("HomePage"))
                .build();
    }
}
