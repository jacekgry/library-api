package com.jacekgry.cognifide.dao;

import com.jacekgry.cognifide.exceptions.BookDoesNotExistException;
import com.jacekgry.cognifide.exceptions.DuplicateBookIdException;
import com.jacekgry.cognifide.model.Book;

public interface BookDAO {

    Book getBookByISBN(String ISBN) throws BookDoesNotExistException, DuplicateBookIdException;
}
