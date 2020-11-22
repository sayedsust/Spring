package com.kas.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;
    private int age;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "author", orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public Author addBook(Book book) {
        this.books.add(book);
        book.setAuthor(this);
        return this;
    }

    public Author removeBook(Book book) {
        book.setAuthor(null);
        this.books.remove(book);
        return this;
    }

    public void removeBooks() {
        Iterator<Book> iterator = this.books.iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();

            book.setAuthor(null);
            iterator.remove();
        }
    }

    public Long getId() {
        return id;
    }

    public Author setId(Long id) {
        this.id = id;
        return this;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public Optional<String> getGenre() {
        return Optional.ofNullable(genre);
    }

    public Author setGenre(String genre) {
        this.genre = genre;
        return this;
    }  

    public int getAge() {
        return age;
    }

    public Author setAge(int age) {
        this.age = age;
        return this;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Author setBooks(List<Book> books) {
        this.books = books;
        return this;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", name=" + name 
                + ", genre=" + genre + ", age=" + age + '}';
    }
    
}
