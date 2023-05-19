package com.model.dto.cartItem;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CartItemRequestDTO implements Validator {

    private String productId;
    private String quantity;

    @Override
    public boolean supports(Class<?> clazz) {
        return CartItemRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CartItemRequestDTO cartItemRequestDTO = (CartItemRequestDTO) target;

        //do something
    }
}
