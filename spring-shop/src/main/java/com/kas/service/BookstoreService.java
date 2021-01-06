package com.kas.service;

import com.kas.dto.AuthorDto;
import com.kas.entity.Author;
import com.kas.entity.Book;
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
    public Page<Author> fetchNextPage(int page, int size) {

        return authorRepository.findAll(PageRequest.of(page, size,Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPageByGenre(int page, int size) {

        return authorRepository.fetchByGenre("History",
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPageByGenreExplicitCount(int page, int size) {

        return authorRepository.fetchByGenreExplicitCount("History",
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPageByGenreNative(int page, int size) {

        return authorRepository.fetchByGenreNative("History",
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPageByGenreNativeExplicitCount(int page, int size) {

        return authorRepository.fetchByGenreNativeExplicitCount("History",
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPagePageable(Pageable pageable) {

        return authorRepository.findAll(pageable);
    }

    public Slice<Author> fetchNextSlice(int page, int size) {

        return authorRepository.fetchAll(PageRequest.of(page, size,
                Sort.by(Sort.Direction.ASC, "age")));
    }

    public Slice<AuthorDto> fetchNextSliceDto(int page, int size) {

        return authorRepository.fetchAllDto(PageRequest.of(page, size,
                Sort.by(Sort.Direction.ASC, "age")));
    }
}
