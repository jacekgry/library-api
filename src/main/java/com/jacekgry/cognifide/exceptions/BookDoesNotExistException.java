package com.jacekgry.cognifide.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookDoesNotExistException extends RuntimeException {

    public BookDoesNotExistException() {
        super("No results found");
    }
}
