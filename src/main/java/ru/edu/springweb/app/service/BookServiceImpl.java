package ru.edu.springweb.app.service;

import org.springframework.stereotype.Service;
import ru.edu.springweb.in.model.Book;
import ru.edu.springweb.out.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAll() {
        return repository.getAll();
    }

    @Override
    public Book getBookById(int id) {
        return repository.getBookById(id);
    }

    @Override
    public Book addBook(Book book) {
        return repository.addBook(book);
    }

    @Override
    public Book updateBook(int id, Book updatedBook) {
        return repository.updateBook(id, updatedBook);
    }

    @Override
    public void deleteBook(int id) {
        repository.deleteBook(id);
    }
}
