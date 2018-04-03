package com.jacekgry.cognifide.service;

import com.jacekgry.cognifide.dao.BookDAO;
import com.jacekgry.cognifide.model.Author;
import com.jacekgry.cognifide.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface BookService {

    public Book getBookByISBN(String ISBN);
    List<Book> getBooksByCategory(String categoryName);
    List<Author> getAuthorsRating();
}
