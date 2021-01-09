package com.kas.service;

import com.kas.dto.AuthorDto;
import com.kas.entity.Author;
import com.kas.entity.Book;
import com.kas.model.AuthorView;
import com.kas.model.AuthorViewDto;
import com.kas.repository.AuthorRepository;
import com.kas.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

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

//    public void persistAuthorWithBooks() {
//
//        Author author = new Author()
//                .setName("Joana Nimar")
//                .setAge(34)
//                .setGenre("History")
//                .addBook(new Book().setTitle("A History of Ancient Prague").setIsbn("001-JN"))
//                .addBook(new Book().setTitle("A People's History").setIsbn("002-JN"));
//
//        authorRepository.save(author);
//    }

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
    public void fetchBooksOfAuthorByIdAndDeleteFirstBook() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        bookRepository.delete(books.remove(0));

        System.out.println(books);
    }
    public Page<Author> fetchNextPage(int page, int size) {

        return authorRepository.findAll(PageRequest.of(page, size,Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Book> fetchNexBooktPage(int page, int size) {

        return bookRepository.findAll(PageRequest.of(page, size,Sort.by(Sort.Direction.ASC, "title")));
    }

}
