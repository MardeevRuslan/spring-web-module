package ru.edu.springweb.in.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.edu.springweb.app.model.BookDto;
import ru.edu.springweb.app.service.BookService;
import ru.edu.springweb.app.mapper.BookMapper;
import ru.edu.springweb.in.model.Book;
import ru.edu.springweb.out.exception.EntityNotFound;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testGetBookById() throws Exception {
        var book = new Book("Test Title", "Test Author");
        var dto = new BookDto("Test Title", "Test Author");
        when(bookService.getBookById(1)).thenReturn(book);
        when(bookMapper.map(book)).thenReturn(dto);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.author").value("Test Author"));

        verify(bookService).getBookById(1);
    }

    @Test
    void testAddBook() throws Exception {
        var book = new Book( "New Title", "New Author");
        var dto = new BookDto( "New Title", "New Author");
        when(bookMapper.map(dto)).thenReturn(book);
        when(bookService.addBook(any())).thenReturn(book);
        when(bookMapper.map(book)).thenReturn(dto);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"New Title\", \"author\": \"New Author\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Title"));

        verify(bookService).addBook(any());
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk());

        verify(bookService).deleteBook(1);
    }

    @Test
    void testHandleEntityNotFound() throws Exception {
        doThrow(new EntityNotFound("Entity not found")).when(bookService).getBookById(99);

        mockMvc.perform(get("/books/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Entity not found"));

        verify(bookService).getBookById(99);
    }
}

