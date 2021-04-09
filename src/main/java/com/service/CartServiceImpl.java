package com.service;

import com.dto.CartDto;
import com.exception.CartException;
import com.model.Cart;
import com.model.CartItem;
import com.model.Product;
import com.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Override
    public Cart createCart() {
        return createNewCart();
    }

    private Cart createNewCart() {
        return cartRepository.save(new Cart());
    }

    @Override
    public void addProductToCart(String productId, int quantity, String cartId) throws CartException {
        Product product = productService.findProduct(productId);
        Cart cart = findCartByIdFromDb(cartId);
        cart.addProductToCart(product, quantity);
        saveCart(cart);
    }

    private Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    private Cart findCartByIdFromDb(String cartId) throws CartException {
        Optional<Cart> cartOptional = cartRepository.findCartById(cartId);
        if(cartOptional.isPresent()){
            return cartOptional.get();
        }else throw new CartException("No cart find with this " + cartId);
    }

    @Override
    public void removeProductFromCart(String productId, String cartId) throws CartException {
        Cart cart = findCartByIdFromDb(cartId);
        cart.removeCartItem(productId);
        saveCart(cart);
    }

    @Override
    public CartDto findCartById(String cartId) throws CartException {
        Cart cart = findCartByIdFromDb(cartId);
        return CartDto.packDto(cart);
    }

    @Override
    public void reduceCartProductQuantity(String cartId, String productId, int quantity) throws CartException {
        Cart cart = findCartByIdFromDb(cartId);
        cart.removeCartItem(productId, quantity);
        saveCart(cart);
    }

    @Override
    public BigDecimal calculateCartTotal(String cartId) throws CartException {
        Cart cart = findCartByIdFromDb(cartId);
        BigDecimal cartTotal = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getCartItemCollection().values()){
            cartTotal = cartTotal.add(cartItem.getTotal());
        }
        return cartTotal;
    }
}
