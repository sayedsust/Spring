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
import java.util.Optional;

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
        Optional<Author> proxy = Optional.of(authorRepository.getOne(1L));
        if (proxy.isPresent()){
            Book book = new Book();
            book.setIsbn("001-MJ");
            book.setTitle("The Canterbury Anthology");
            book.setAuthor(proxy.get());

            bookRepository.save(book);
        }
    }

    @Transactional
    public void insertNewBook() {
        Optional<Author> author = Optional.of(authorRepository.getOne(4L));
        // or, less efficient since a SELECT is triggered
        // Author author = authorRepository.findByName("Joana Nimar");
        if (author.isPresent()){
            Book book = new Book();
            book.setIsbn("003-JN");
            book.setTitle("History Of Present");
            book.setAuthor(author.get());
            bookRepository.save(book);
        }

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


    @Transactional(readOnly = true)
    public void displayAuthorWithBooks() {

        Optional<Author> author = authorRepository.findByName("Joana Nimar");
        if (author.isPresent()){
            System.out.println(author + "  Books: " + author.get().getBooks());
        }

    }

    @Transactional
    public void deleteBookOfAuthor() {

        Optional<Author> author = authorRepository.findByName("Alicia Tom");
        if (author.isPresent()){
            Book book = author.get().getBooks().get(0);
            author.get().removeBook(book); // use removeBook() helper
        }
    }

    @Transactional
    public void deleteAllBooksOfAuthor() {
        Optional<Author> author = authorRepository.findByName("Joana Nimar");
        if (author.isPresent()){
            author.get().removeBooks(); // use removeBooks() helper
        }
    }

    @Transactional
    public void fetchBooksOfAuthorByIdAndDeleteFirstBook() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        bookRepository.delete(books.remove(0));

        System.out.println(books);
    }

}
