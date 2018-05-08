package com.jacekgry.cognifide.service;

import com.jacekgry.cognifide.dao.BookDAO;
import com.jacekgry.cognifide.exceptions.BookNotFoundException;
import com.jacekgry.cognifide.exceptions.DuplicateBookIdException;
import com.jacekgry.cognifide.model.Author;
import com.jacekgry.cognifide.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {


    @Autowired
    private BookDAO bookDAO;

    public Book getBookByISBN(String ISBN) {

        List<Book> books = bookDAO.getBooksByISBN(ISBN);
        if (books.size() == 0) throw new BookNotFoundException();
        else if (books.size() > 1) throw new DuplicateBookIdException();
        else return books.get(0);
    }

    @Override
    public List<Book> getBooksByCategory(String categoryName) {
        List<Book> books = bookDAO.getBooksByCategory(categoryName);
        return books;
    }

    @Override
    public List<Author> getAuthorsRating() {
        Map<String, Author> authorsRating = new LinkedHashMap<>();
        for (Book book : bookDAO.getAllBooks())
            if (book.getAuthors() != null) {
                for (String authorName : book.getAuthors()) {
                    if (book.getAverageRating() != null) {
                        if (authorsRating.containsKey(authorName)) {
                            authorsRating.get(authorName).addRatedBook(book.getAverageRating());
                        } else {
                            authorsRating.put(authorName, new Author(authorName, book.getAverageRating()));
                        }
                    }
                }
            }

        return new ArrayList<>(authorsRating.values());
    }
}
