package com.kas.service;

import com.kas.entity.Author;
import com.kas.entity.Book;
import com.kas.repository.AuthorRepository;
import com.kas.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void persistAuthorWithBooks() {

        Author author = new Author()
                .setName("Joana Nimar")
                .setAge(34)
                .setGenre("History")
                .addBook(new Book()
                        .setTitle("A History of Ancient Prague")
                        .setIsbn("001-JN"))
                .addBook(new Book()
                        .setTitle("A People's History")
                        .setIsbn("002-JN"));

        authorRepository.save(author);
    }

    @Transactional
    public void addBookToAuthor() {
        // behind getOne() we have EntityManager#getReference()
        Author proxy = authorRepository.getOne(1L);

        Book book = new Book();
        book.setIsbn("001-MJ");
        book.setTitle("The Canterbury Anthology");
        book.setAuthor(proxy);

        bookRepository.save(book);
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

    public void fetchBooksOfAuthorById() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        System.out.println(books);
    }

    public void fetchPageBooksOfAuthorById() {
        Page<Book> books = bookRepository.fetchPageBooksOfAuthorById(4L,
                PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "title")));

        books.get().forEach(System.out::println);
    }

    @Transactional
    public void fetchBooksOfAuthorByIdAndAddNewBook() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        Book book = new Book();
        book.setIsbn("004-JN");
        book.setTitle("History Facts");
        book.setAuthor(books.get(0).getAuthor());

        books.add(bookRepository.save(book));

        System.out.println(books);
    }

    @Transactional(readOnly = true)
    public void displayAuthorWithBooks() {

        Author author = authorRepository.findByName("Joana Nimar");

        System.out.println(author + "  Books: " + author.getBooks());
    }

    @Transactional
    public void deleteBookOfAuthor() {

        Author author = authorRepository.findByName("Alicia Tom");
        Book book = author.getBooks().get(0);

        author.removeBook(book); // use removeBook() helper        
    }

    @Transactional
    public void deleteAllBooksOfAuthor() {
        Author author = authorRepository.findByName("Joana Nimar");
        author.removeBooks(); // use removeBooks() helper    
    }

    @Transactional
    public void fetchBooksOfAuthorByIdAndDeleteFirstBook() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        bookRepository.delete(books.remove(0));

        System.out.println(books);
    }

}
