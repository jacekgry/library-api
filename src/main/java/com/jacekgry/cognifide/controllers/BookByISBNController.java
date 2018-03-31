package com.jacekgry.cognifide.controllers;

import com.jacekgry.cognifide.model.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookByISBNController {



    @RequestMapping("/api/book/{ISBN}")
    public Book getBookByISBN(@PathVariable Integer ISBN){


        return null;
    }

}
