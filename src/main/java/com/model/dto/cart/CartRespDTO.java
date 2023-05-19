package com.model.dto.cart;

import com.model.dto.cartItem.CartItemRespDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartRespDTO {

    private BigDecimal totalAmount;
    private String fullName;

    private List<CartItemRespDTO> items;
}
