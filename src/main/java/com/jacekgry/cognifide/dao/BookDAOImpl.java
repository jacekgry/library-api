package com.jacekgry.cognifide.dao;


import com.jacekgry.cognifide.model.Book;
import com.jacekgry.cognifide.utils.JsonUtils;
import com.jacekgry.cognifide.utils.PseudoDB;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookDAOImpl implements BookDAO {


    @PostConstruct
    public void init() {
        JsonUtils jsonUtils = new JsonUtils();
        PseudoDB.setBooks(jsonUtils.encodeJson().getItems());
    }


    @Override
    public List<Book> getBooksByISBN(String ISBN) {

        List<Book> books = PseudoDB.getBooks().stream().filter(book -> {
            try {
                return book.getIsbn().equals(ISBN);
            } catch (Exception e) {
                return false;
            }
        }).collect(Collectors.toList());

        return books;
    }

    @Override
    public List<Book> getBooksByCategory(String categoryName) {
        List<Book> books = PseudoDB.getBooks().stream().filter(book -> {
            try {
                return book.getCategories().contains(categoryName);
            } catch (Exception e) {
                return false;
            }

        }).collect(Collectors.toList());
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        return PseudoDB.getBooks();
    }
}
