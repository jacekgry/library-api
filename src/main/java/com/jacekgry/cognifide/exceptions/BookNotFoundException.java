package com.jacekgry.cognifide.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super("No results found");
    }
}
