package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Product product;
    private int quantity = 1;
    private BigDecimal total;


    @NotNull
    private BigDecimal calculateCartItemPrice() {
        return product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    public void increaseCartItemQuantity(int quantity){
        this.quantity += quantity;
        total = calculateCartItemPrice();
    }

    public void decreaseCartItemQuantity(int quantity){
        this.quantity -= quantity;
        total = calculateCartItemPrice();
    }
}
