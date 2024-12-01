package ru.edu.springweb.in.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.edu.springweb.app.model.BookDto;
import ru.edu.springweb.app.service.BookService;
import ru.edu.springweb.app.mapper.BookMapper;
import ru.edu.springweb.out.exception.EntityNotFound;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;
    private final BookMapper mapper;

    public BookController(BookService service, BookMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllUsers() {
        var books = service.getAll();
        return ResponseEntity.ok(mapper.map(books));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") int id) {
        var book = service.getBookById(id);
        return ResponseEntity.ok(mapper.map(book));
    }


    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        var book = service.addBook(mapper.map(bookDto));
        return ResponseEntity.ok(mapper.map(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") int id, @RequestBody BookDto updatedBook) {
        var book = service.updateBook(id, mapper.map(updatedBook));
        return ResponseEntity.ok(mapper.map(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
        service.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка: " + ex.getMessage());
    }
}
