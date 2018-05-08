package com.jacekgry.cognifide.dao;


import com.jacekgry.cognifide.model.Book;
import com.jacekgry.cognifide.utils.JsonUtils;
import com.jacekgry.cognifide.utils.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookDAOImpl implements BookDAO {


    @Autowired
    private JsonUtils jsonUtils;

    @PostConstruct
    public void init() {
        DB.setBooks(jsonUtils.encodeJson().getItems());
    }


    @Override
    public List<Book> getBooksByISBN(String ISBN) {

        List<Book> books = DB.getBooks().stream().filter(book -> {
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
        List<Book> books = DB.getBooks().stream().filter(book -> {
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
        return DB.getBooks();
    }
}
