package com.jacekgry.cognifide.controllers;

import com.jacekgry.cognifide.model.Author;
import com.jacekgry.cognifide.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorsRatingController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/api/rating")
    public List<Author> getAuthorsRating() {
        return bookService.getAuthorsRating();
    }

}
