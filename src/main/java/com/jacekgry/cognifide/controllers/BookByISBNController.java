package com.jacekgry.cognifide.controllers;

import com.jacekgry.cognifide.model.Book;
import com.jacekgry.cognifide.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookByISBNController {


    @Autowired
    private BookService bookService;

    @RequestMapping("/api/book/{ISBN}")
    public Book getBookByISBN(@PathVariable String ISBN) {

        return bookService.getBookByISBN(ISBN);
    }


}
