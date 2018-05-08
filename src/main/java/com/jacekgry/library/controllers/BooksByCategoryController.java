package com.jacekgry.cognifide.controllers;

import com.jacekgry.cognifide.model.Book;
import com.jacekgry.cognifide.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksByCategoryController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/api/category/{categoryName}/books")
    public List<Book> getBooksByCategory(@PathVariable String categoryName) {
        return bookService.getBooksByCategory(categoryName);

    }
}
