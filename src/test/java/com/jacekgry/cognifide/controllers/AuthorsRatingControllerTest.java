package com.jacekgry.cognifide.controllers;

import com.jacekgry.cognifide.model.Author;
import com.jacekgry.cognifide.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthorsRatingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private AuthorsRatingController authorsRatingController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authorsRatingController).setControllerAdvice(new ExceptionController()).build();
    }

    @Test
    public void getAuthorsRaringTest() throws Exception {

        Author author1 = new Author("Adam Mickiewicz", 5.0);
        Author author2 = new Author("Julisz Slowacki", 4.0);
        Author author3 = new Author("Boleslaw Prus", 4.5);

        List<Author> authors = new ArrayList<>();

        authors.add(author1);
        authors.add(author2);
        authors.add(author3);

        when(bookService.getAuthorsRating()).thenReturn(authors);


        mockMvc.perform(get("/api/rating"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].*", hasSize(2)))
                .andExpect(jsonPath("$[1].averageRating").value(4.0));
    }

}