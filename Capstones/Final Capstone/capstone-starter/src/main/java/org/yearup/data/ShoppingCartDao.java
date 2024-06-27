package org.yearup.data;

import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    ShoppingCart addToCartByProductId(int userId, int productId);
    ShoppingCart updateCartItem(int userId, int productId, ShoppingCartItem shoppingCartItem);
    ShoppingCart deleteCart(int userId);
}
