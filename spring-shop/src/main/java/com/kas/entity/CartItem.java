package com.kas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;
    private BigDecimal amount;

    public CartItem(Book book) {
        this.book = book;
        this.amount = book.getPrice();
        this.quantity = 1;
    }

    public CartItem() { }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void incrementQuantityAndAmount() {
        quantity++;
        amount = amount.add(book.getPrice());
    }

    public boolean decrementQuantityAndAmount() {
        quantity--;
        amount = amount.subtract(book.getPrice());
        return quantity == 0;
    }

    public String getAuthor(){
        return book.getAuthor().get().getName().get();
    }
}
