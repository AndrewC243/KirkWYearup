package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.data.OrderDao;
import org.yearup.data.UserDao;
import org.yearup.exceptions.PrincipalNotFoundException;
import org.yearup.models.Order;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Order> addOrder(Principal principal) {
        if (principal == null) throw new PrincipalNotFoundException("No principal provided");
        return ResponseEntity.ok(orderDao.createOrder(userDao.getIdByUsername(principal.getName())));
    }
}
