package com.kas;

import com.kas.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {
    
    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }        

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\nInsert author with books  ...");
            System.out.println("---------------------------------------------");
            bookstoreService.insertAuthorWithBooks();
            System.out.println("---------------------------------------------");

            System.out.println("\nInsert new book to an author ...");
            System.out.println("---------------------------------------------");
            bookstoreService.insertNewBook();
            System.out.println("---------------------------------------------");

            System.out.println("\nDelete a book of an author...");
            System.out.println("---------------------------------------------");
            bookstoreService.deleteBookOfAuthor();
            System.out.println("---------------------------------------------");
            
            System.out.println("\nDelete all book of an author...");
            System.out.println("---------------------------------------------");
            bookstoreService.deleteAllBooksOfAuthor();
            System.out.println("---------------------------------------------");


        };
    }
}
