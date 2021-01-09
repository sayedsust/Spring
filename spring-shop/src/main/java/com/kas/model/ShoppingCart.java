package com.kas.model;

import com.kas.entity.Book;
import com.kas.entity.CartItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SessionScope
@Component
public class ShoppingCart implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingCart.class);
    private static final long serialVersionUID = 1L;

    private final List<CartItem> cartItems = new ArrayList<>();
    private int totalQuantity = 0;
    private BigDecimal totalAmount = BigDecimal.ZERO;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void addProduct(Book book) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getBook().getId() == book.getId()) {
                cartItem.incrementQuantityAndAmount();
                incrementTotalQuantityAndAmount(cartItem.getBook().getPrice());
                return;
            }
        }
        cartItems.add(new CartItem(book));
        incrementTotalQuantityAndAmount(book.getPrice());
    }

    public void removeProduct(Book book) {
        LOG.debug("Removing product: {}", book);

        Iterator<CartItem> it = cartItems.iterator();
        while (it.hasNext()) {
            CartItem cartItem = it.next();

            if (cartItem.getBook().getId() == book.getId()) {
                if (cartItem.decrementQuantityAndAmount()) {
                    it.remove();
                }
                decrementTotalQuantityAndAmount(cartItem.getAmount());
            }
        }
    }

    public void empty() {
        cartItems.clear();
        totalQuantity = 0;
        totalAmount = BigDecimal.ZERO;
    }

    private void incrementTotalQuantityAndAmount(BigDecimal productPrice) {
        totalQuantity++;
        totalAmount = totalAmount.add(productPrice);
    }

    private void decrementTotalQuantityAndAmount(BigDecimal productPrice) {
        totalQuantity--;
        totalAmount = totalAmount.subtract(productPrice);
    }
}
