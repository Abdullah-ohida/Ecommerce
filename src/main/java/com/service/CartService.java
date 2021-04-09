package com.service;

import com.dto.CartDto;
import com.exception.CartException;
import com.model.Cart;

import java.math.BigDecimal;

public interface CartService {
    public Cart createCart();
    public void addProductToCart(String productId, int quantity, String cartId) throws CartException;
    public void removeProductFromCart(String productId,  String cartId) throws CartException;
    public CartDto findCartById(String cartId) throws CartException;
    public void reduceCartProductQuantity(String cartId, String productId, int quantity) throws CartException;
    public BigDecimal calculateCartTotal(String cartId) throws CartException;
}
