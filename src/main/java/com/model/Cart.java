package com.model;

import com.exception.CartException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cart {
    @Id
    private String cartId;
    private Map<String, CartItem> cartItemCollection = new HashMap<>();
    private BigDecimal cartTotal;

    public void addProductToCart(Product product, int quantity){
        String productId = product.getId();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        if(cartItemCollection.containsKey(productId)){
            cartItemCollection.get(productId).increaseCartItemQuantity(quantity);
        }
        cartItemCollection.put(productId, cartItem);
        cartTotal = calculateCartTotal();
    }

    private BigDecimal calculateCartTotal() {
        BigDecimal cartItemTotal = BigDecimal.ZERO;
        for(CartItem item : cartItemCollection.values()){
            cartItemTotal = cartTotal.add(item.getTotal());
        }
      return cartItemTotal;
    }

    public void removeCartItem(String productId){
        cartItemCollection.remove(productId);
        cartTotal = calculateCartTotal();
    }

    public void removeCartItem(String productId, int quantity) throws CartException {
        if(cartItemCollection.containsKey(productId)){
            cartItemCollection.get(productId).decreaseCartItemQuantity(quantity);
        }else throw new CartException("Cart Item is not in cart");
        cartTotal = calculateCartTotal();
    }
}
