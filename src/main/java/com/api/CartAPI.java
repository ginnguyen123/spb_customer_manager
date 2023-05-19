package com.api;


import com.service.cart.ICartService;
import com.service.cartItem.ICartItemService;
import com.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private AppUtils appUtils;
    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    private ICartService cartService;





}
