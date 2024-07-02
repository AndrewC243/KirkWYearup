package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepository {
    private BasicDataSource dataSource;

    public CategoryRepository(String url, String username, String password) {
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    public List<Category> getAllCategories(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/northwind";
        List<Category> categories = new ArrayList<Category>();

        String query = "SELECT * FROM categories";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("CategoryID");
                    String name = rs.getString("CategoryName");
                    String description = rs.getString("Description");
                    categories.add(new Category(id, name, description));
                }
            }
        } catch (SQLException ignored) {}
        return categories;
    }
    public Optional<Category> getCategoryById(int id) {
        String query = "SELECT * FROM categories WHERE CategoryID = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("CategoryName");
                    String description = rs.getString("Description");
                    return Optional.of(new Category(id, name, description));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public Category createCategory(Category category) {
        String query = "INSERT INTO categories(CategoryName, Description) VALUES (?,?)";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getCategoryDescription());
        } catch (SQLException ex) { ex.printStackTrace(); }
        return category;
    }

    public Category updateCategory(Category category) {
        String query = "UPDATE categories SET CategoryName = ?, Description = ? WHERE CategoryID = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getCategoryDescription());
            ps.setInt(3, category.getCategoryId());
        } catch (SQLException ignored) {}
        return category;
    }

    public void deleteCategory(int id) {
        String query = "DELETE FROM categories WHERE CategoryID = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ignored) {}
    }
}
