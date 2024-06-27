package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.exceptions.PrincipalNotFoundException;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;

// convert this class to a REST controller
// only logged in users should have access to these actions
@RestController
@CrossOrigin
@RequestMapping("cart")
public class ShoppingCartController {
    // a shopping cart requires
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private UserDao userDao;


    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<ShoppingCart> getCart(Principal principal) {
        if (principal == null) throw new PrincipalNotFoundException("No principal");
        // get the currently logged in username
        String userName = principal.getName();
        // find database user by userId
        User user = userDao.getByUserName(userName);
        int userId = user.getId();

        // use the shoppingcartDao to get all items in the cart and return the cart
        return ResponseEntity.ok(shoppingCartDao.getByUserId(userId));
    }

    @PostMapping("/products/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ShoppingCart> addProduct(@PathVariable int id, Principal principal) {
        if (principal == null) throw new PrincipalNotFoundException("No principal");
        return ResponseEntity
                .ok(shoppingCartDao.addToCartByProductId(userDao.getIdByUsername(principal.getName()), id));
    }

    @PutMapping("/products/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ShoppingCart> updateProduct(@PathVariable int id, @RequestBody ShoppingCartItem sci, Principal principal) {
        if (principal == null) throw new PrincipalNotFoundException("No principal");
        User user = userDao.getByUserName(principal.getName());
        return ResponseEntity.ok(shoppingCartDao.updateCartItem(user.getId(), id, sci));
    }

    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart
    @DeleteMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<ShoppingCart> deleteProduct(Principal principal) {
        if (principal == null) throw new PrincipalNotFoundException("No principal");
        return ResponseEntity.ok(shoppingCartDao.deleteCart(userDao.getIdByUsername(principal.getName())));
    }
}
