package com.kas.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private BigDecimal price;
    private String desscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Optional<String> getIsbn() {
        return Optional.ofNullable(isbn);
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Optional<Author> getAuthor() {
        return Optional.ofNullable(author);
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDesscription() {
        return desscription;
    }

    public void setDesscription(String desscription) {
        this.desscription = desscription;
    }

    @Override
    public boolean equals(Object obj) {           
        
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof Book)) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        return id != null && id.equals(((Book) obj).id);
    }

    @Override
    public int hashCode() {
        return 2021;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", isbn=" + isbn + '}';
    }
    
}
