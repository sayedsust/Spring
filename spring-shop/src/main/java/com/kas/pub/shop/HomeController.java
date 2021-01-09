package com.kas.pub.shop;

import com.kas.entity.Author;
import com.kas.entity.Book;
import com.kas.service.BookstoreService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    private final BookstoreService bookstoreService;

    public HomeController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @ModelAttribute("module")
    String module() {
        return "home";
    }

    @GetMapping("/")
    public String fetchAuthors(Model model) {

        final int currentPage = 1;
        final int pageSize = 12;

        Page<Author> authorPage = bookstoreService.fetchNextPage(currentPage, pageSize);
        model.addAttribute("authorPage", authorPage);

        int totalPages = authorPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        Page<Book> bookPage = bookstoreService.fetchNexBooktPage(currentPage, pageSize);
        model.addAttribute("bookPage", bookPage);

        totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "home";
    }
}
