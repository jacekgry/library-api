package com.jacekgry.cognifide.utils;

import com.jacekgry.cognifide.model.Author;
import com.jacekgry.cognifide.model.Book;

import java.util.List;

public class DB {

    private static List<Book> books;
    public static List<Book> getBooks() {
        return books;
    }

    public static void setBooks(List<Book> listOfBooks) {

        books = listOfBooks;
    }
}

