package com.kas.service;

import com.kas.entity.Author;
import com.kas.entity.Book;
import com.kas.repository.AuthorRepository;
import com.kas.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void insertAuthorWithBooks() {

        Author author = new Author();
        author.setName("Alicia Tom");
        author.setAge(38);
        author.setGenre("Anthology");

        Book book = new Book();
        book.setIsbn("001-AT");
        book.setTitle("The book of swords");

        author.addBook(book); // use addBook() helper

        authorRepository.save(author);
    }

    @Transactional
    public void insertNewBook() {
        Author author = authorRepository.getOne(4L);
        // or, less efficient since a SELECT is triggered
        // Author author = authorRepository.findByName("Joana Nimar");

        Book book = new Book();
        book.setIsbn("003-JN");
        book.setTitle("History Of Present");
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Transactional
    public void deleteBookOfAuthor() {

        Author author = authorRepository.fetchByName("Alicia Tom");
        Book book = author.getBooks().get(0);

        author.removeBook(book); // use removeBook() helper        
    }

    @Transactional
    public void deleteAllBooksOfAuthor() {
        Author author = authorRepository.fetchByName("Joana Nimar");
        author.removeBooks(); // use removeBooks() helper    
    }

}
