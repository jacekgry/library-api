package com.jacekgry.cognifide.controllers;

import com.jacekgry.cognifide.dao.BookDAO;
import com.jacekgry.cognifide.exceptions.BookDoesNotExistException;
import com.jacekgry.cognifide.exceptions.DuplicateBookIdException;
import com.jacekgry.cognifide.model.Book;
import com.jacekgry.cognifide.utils.PseudoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BookByISBNController {



    @Autowired
    private BookDAO bookDAO;

    @RequestMapping("/api/book/{ISBN}")
    public Book getBookByISBN(@PathVariable String ISBN) throws BookDoesNotExistException {

//        return PseudoDB.getBooks();
        try {
            return bookDAO.getBookByISBN(ISBN);
        } catch (BookDoesNotExistException e) {
            e.printStackTrace();
            throw new BookDoesNotExistException();

        } catch (DuplicateBookIdException e) {
            e.printStackTrace();
        }
        return null;

    }

}
