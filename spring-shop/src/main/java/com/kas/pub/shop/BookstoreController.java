package com.kas.pub.shop;

import com.kas.entity.Author;
import com.kas.repository.BookRepository;
import com.kas.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookstoreController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private  BookstoreService bookstoreService;


//    /*
//    Keyset Pagination
//     */
//    @GetMapping("/authors/{id}/{limit}")
//    public AuthorView fetchAuthors(@PathVariable long id, @PathVariable int limit) {
//
//        return bookstoreService.fetchNextPage(id, limit);
//    }
//
//    @GetMapping("/dto/authors/{id}/{limit}")
//    public AuthorViewDto fetchAuthorsDto(@PathVariable long id, @PathVariable int limit) {
//
//        return bookstoreService.fetchNextPageDto(id, limit);
//    }
    /*
    Slice API
     */
//    @GetMapping("/authors/{page}/{size}")
//    public Slice<Author> fetchAuthorsSlice(@PathVariable int page, @PathVariable int size) {
//
//        return bookstoreService.fetchNextSlice(page, size);
//    }

//    @GetMapping("/authors/{page}/{size}")
//    public Slice<AuthorDto> fetchAuthorsDto(@PathVariable int page, @PathVariable int size) {
//
//        return bookstoreService.fetchNextSliceDto(page, size);
//    }

    /*
    Paging API
     */
    @GetMapping("/authors/{page}/{size}")
    public String fetchAuthors(Model model, @PathVariable int page, @PathVariable int size) {

        final int currentPage = page;
        final int pageSize = size;

        Page<Author> authorPage = bookstoreService.fetchNextPage(page, size);

        model.addAttribute("authorPage", authorPage);

        int totalPages = authorPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "home";
    }

    @GetMapping("/book/{id}")
    public String showProductDetails(@PathVariable long id, Model model){
        model.addAttribute("book",bookRepository.findById(id).get());
        return "product-detail";
    }

//    @GetMapping("/authorsByGenre/{page}/{size}")
//    public Page<Author> fetchAuthorsByGenre(@PathVariable int page, @PathVariable int size) {
//
//        return bookstoreService.fetchNextPageByGenre(page, size);
//    }
//
//    @GetMapping("/authorsByGenreExplicitCount/{page}/{size}")
//    public Page<Author> fetchAuthorsByGenreExplicitCount(@PathVariable int page, @PathVariable int size) {
//
//        return bookstoreService.fetchNextPageByGenreExplicitCount(page, size);
//    }
//
//    @GetMapping("/authorsByGenreNative/{page}/{size}")
//    public Page<Author> fetchAuthorsByGenreNative(@PathVariable int page, @PathVariable int size) {
//
//        return bookstoreService.fetchNextPageByGenreNative(page, size);
//    }
//
//    @GetMapping("/authorsByGenreNativeExplicitCount/{page}/{size}")
//    public Page<Author> fetchAuthorsByGenreNativeExplicitCount(@PathVariable int page, @PathVariable int size) {
//
//        return bookstoreService.fetchNextPageByGenreNativeExplicitCount(page, size);
//    }
//
//    @GetMapping("/authors")
//    // Request example: http://localhost:8080/authors?page=1&size=3&sort=name,desc
//    public Page<Author> fetchAuthors(Pageable pageable) {
//
//        return bookstoreService.fetchNextPagePageable(pageable);
//    }
}
