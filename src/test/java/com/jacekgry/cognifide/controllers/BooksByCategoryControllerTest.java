package com.jacekgry.cognifide.controllers;

import com.jacekgry.cognifide.CognifideApplication;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CognifideApplication.class)
@WebAppConfiguration
public class BooksByCategoryControllerTest {


    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BooksByCategoryController booksByCategoryController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(booksByCategoryController).setControllerAdvice(new ExceptionController()).build();
    }

    @Test
    public void getBooksOfEmptyCategoryTest() throws Exception {
        when(bookService.getBooksByCategory("csharp")).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/api/category/csharp/books")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getBooksOfCategoryTest() throws Exception {
        List<Book> javaBooks = new ArrayList<>();
        Book book1 = new Book("111", "good java book", 111);
        Book book2 = new Book("123", "better java book", 222);
        Book book3 = new Book("321", "best java book", 44);
        javaBooks.add(book1);
        javaBooks.add(book2);
        javaBooks.add(book3);

        when(bookService.getBooksByCategory("java")).thenReturn(javaBooks);

        mockMvc.perform(get("/api/category/java/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title").value("good java book"))
                .andExpect(jsonPath("$[1].isbn").value("123"))
                .andExpect(jsonPath("$[2].pageCount").value(44));

    }


}