package com.jacekgry.cognifide.dao;


import com.jacekgry.cognifide.exceptions.BookDoesNotExistException;
import com.jacekgry.cognifide.exceptions.DuplicateBookIdException;
import com.jacekgry.cognifide.model.Book;
import com.jacekgry.cognifide.utils.JsonParser;
import com.jacekgry.cognifide.utils.PseudoDB;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookDAOImpl implements BookDAO {


    @PostConstruct
    public void init() {
        JsonParser jsonParser = new JsonParser();
        PseudoDB.setBooks(jsonParser.encodeJson().getItems());

    }


    @Override
    public Book getBookByISBN(String ISBN) throws BookDoesNotExistException, DuplicateBookIdException {

        List<Book> books = PseudoDB.getBooks().stream().filter(book -> {
            try {
                return book.getIsbn().equals(ISBN);
            } catch (Exception e) {
                return false;
            }
        }).collect(Collectors.toList());

        if (books.size() == 0) throw new BookDoesNotExistException();
        else if (books.size() > 1)
            throw new DuplicateBookIdException();
        else return books.get(0);
    }
}
