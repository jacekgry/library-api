package com.jacekgry.cognifide.service;

import com.jacekgry.cognifide.dao.BookDAO;
import com.jacekgry.cognifide.exceptions.BookNotFoundException;
import com.jacekgry.cognifide.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {

    private MockMvc mockMvc;

    @Mock
    private BookDAO bookDAO;

    @InjectMocks
    private BookServiceImpl bookService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookService).build();
    }

    @Test(expected = BookNotFoundException.class)
    public void getNonExistingISBN() {
        when(bookDAO.getBooksByISBN("333")).thenReturn(new ArrayList<>());
        Book book = bookService.getBookByISBN("333");
    }

    @Test
    public void getAuthorsRatingTest() {

        List<String> authors1 = new ArrayList<>();
        List<String> authors2 = new ArrayList<>();
        List<String> authors3 = new ArrayList<>();
        List<String> authors4 = new ArrayList<>();
        List<String> authors5 = new ArrayList<>();

        authors1.add("John");
        authors1.add("Joe");
        authors1.add("Mark");

        authors2.add("Mike");
        authors2.add("John");
        authors2.add("Olaf");

        authors3.add("Eric");
        authors3.add("Joe");

        authors4.add("Anne");

        authors5.add("John");
        authors5.add("Joe");
        authors5.add("Olaf");
        authors5.add("Anne");
        authors5.add("Asdasd");

        Book book1 = new Book(authors1, 1.0);
        Book book2 = new Book(authors2, 2.0);
        Book book3 = new Book(authors3, 3.5);
        Book book4 = new Book(authors4, 4.0);
        Book book5 = new Book(authors5, null);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        when(bookDAO.getAllBooks()).thenReturn(books);

        assertEquals(7, bookService.getAuthorsRating().size());

        assertTrue(bookService.getAuthorsRating().get(0).getAverageRating().equals(1.5));
        assertTrue(bookService.getAuthorsRating().get(1).getAverageRating().equals(2.25));
        assertTrue(bookService.getAuthorsRating().get(2).getAverageRating().equals(1.0));
        assertTrue(bookService.getAuthorsRating().get(3).getAverageRating().equals(2.0));
    }
}