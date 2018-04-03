package com.jacekgry.cognifide.dao;

import com.jacekgry.cognifide.model.Book;

import java.util.List;

public interface BookDAO {

    List<Book> getBooksByISBN(String isbn);

    List<Book> getBooksByCategory(String categoryName);

    List<Book> getAllBooks();
}
