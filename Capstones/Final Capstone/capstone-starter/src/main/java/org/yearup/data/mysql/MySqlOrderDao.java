package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.OrderDao;
import org.yearup.data.ProfileDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Order;
import org.yearup.models.OrderLineItem;
import org.yearup.models.Profile;
import org.yearup.models.ShoppingCart;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlOrderDao extends MySqlDaoBase implements OrderDao {
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private ProfileDao profileDao;

    public MySqlOrderDao(DataSource ds) { super(ds); }

    @Override
    public Order createOrder(int userId) {
        Order order = new Order();
        String sql = "{CALL CreateOrder(?,?)}";

        try (Connection conn = getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, userId);
            cs.setBigDecimal(2, BigDecimal.ZERO);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) order = mapRow(rs);
            }
        } catch (SQLException ignored) {}
        order.setLineItems(convertShoppingCartItems(order.getOrderId(), userId));
        return order;
    }

    private List<OrderLineItem> convertShoppingCartItems(int orderId, int userId) {
        String sql = "{CALL ConvertSCI(?,?,0.0)}";
        List<OrderLineItem> items = new ArrayList<>();

        try (Connection conn = getConnection(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, orderId);
            cs.setInt(2, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                items.add(mapLineItemRow(rs));
            }
        } catch (SQLException ignored) { ignored.printStackTrace(); }
        return items;
    }

    private OrderLineItem mapLineItemRow(ResultSet rs) throws SQLException {
        OrderLineItem item = new OrderLineItem();
        item.setOrderLineItemId(rs.getInt("order_line_item_id"));
        item.setOrderId(rs.getInt("order_id"));
        item.setProductId(rs.getInt("product_id"));
        item.setSalesPrice(rs.getBigDecimal("sales_price"));
        item.setQuantity(rs.getInt("quantity"));
        item.setDiscount(rs.getBigDecimal("discount"));

        return item;
    }

    private Order mapRow(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setDate(rs.getTimestamp("date").toLocalDateTime());
        order.setAddress(rs.getString("address"));
        order.setCity(rs.getString("city"));
        order.setState(rs.getString("state"));
        order.setZip(rs.getString("zip"));
        order.setShippingAmount(rs.getBigDecimal("shipping_amount"));
        return order;
    }
}
