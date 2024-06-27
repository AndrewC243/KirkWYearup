package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.exceptions.ResourceNotFoundException;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {
    @Autowired
    private ProductDao productDao;

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                ShoppingCart cart = new ShoppingCart();
                while (rs.next()) cart.add(mapRow(rs));
                return cart;
            }
        } catch (SQLException ignored) {}
        throw new ResourceNotFoundException("Could not find shopping cart for user with id " + userId);
    }

    @Override
    public ShoppingCart addToCartByProductId(int userId, int productId) {
        String sql = "INSERT INTO shopping_cart (user_id, product_id, quantity) VALUES (?,?,?)";
        ShoppingCart cart = getByUserId(userId);
        if (cart.contains(productId)) {
            cart.get(productId).setQuantity(cart.get(productId).getQuantity() + 1);
            return updateCartItem(userId, cart.get(productId).getProductId(), cart.get(productId));
        }

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException ignored) {}

        return getByUserId(userId);
    }

    public ShoppingCart updateCartItem(int userId, int productId, ShoppingCartItem item) {
        String sql = "UPDATE shopping_cart SET quantity = ? WHERE user_id = ? AND product_id = ?";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, item.getQuantity());
            ps.setInt(2, userId);
            ps.setInt(3, productId);
            ps.executeUpdate();
        } catch (SQLException ignored) {}
        return getByUserId(userId);
    }

    public ShoppingCart deleteCart(int userId) {
        String sql = "DELETE FROM shopping_cart WHERE user_id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException ignored) {}
        return getByUserId(userId);
    }

    public ShoppingCartItem mapRow(ResultSet rs) throws SQLException {
        ShoppingCartItem sci = new ShoppingCartItem();
        sci.setProduct(productDao.getById(rs.getInt("product_id")));
        sci.setQuantity(rs.getInt("quantity"));
        return sci;
    }
}
