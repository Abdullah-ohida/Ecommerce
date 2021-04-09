package com.dto;

import com.model.Cart;
import com.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private String id;
    private BigDecimal cartTotal;
    private Map<String, CartItem> cartItemCollection;


    public static Cart unpackDto(CartDto cartDto){
        Cart cart = new Cart();
        cart.setCartItemCollection(cartDto.getCartItemCollection());
        cart.setCartTotal(cartDto.getCartTotal());
        return cart;
    }

    public static CartDto packDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getCartId());
        cartDto.setCartItemCollection(cart.getCartItemCollection());
        cartDto.setCartTotal(cart.getCartTotal());
        return cartDto;
    }
}
