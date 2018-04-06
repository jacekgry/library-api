package com.jacekgry.cognifide.controllers;

import com.jacekgry.cognifide.CognifideApplication;
import com.jacekgry.cognifide.exceptions.BookNotFoundException;
import com.jacekgry.cognifide.model.Book;
import com.jacekgry.cognifide.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CognifideApplication.class)
@WebAppConfiguration
public class BookByISBNControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookByISBNController bookByISBNController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(bookByISBNController).setControllerAdvice(new ExceptionController()).build();
    }

    @Test
    public void getBookByISBNTest() throws Exception {
        when(bookService.getBookByISBN("123")).thenReturn(new Book("123", "Lord of the rings", 456));
        when(bookService.getBookByISBN("nonexisting")).thenThrow(new BookNotFoundException());


        mockMvc.perform(get("/api/book/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.isbn").value("123"))
                .andExpect(jsonPath("$.title").value("Lord of the rings"))
                .andExpect(jsonPath("$.pageCount").value(456));


    }

    @Test
    public void getNonExistingBookTest() throws Throwable {
        when(bookService.getBookByISBN("nonexisting")).thenThrow(new BookNotFoundException());
        mockMvc.perform(get("/api/book/nonexisting")).andExpect(status().isNotFound());
    }
}