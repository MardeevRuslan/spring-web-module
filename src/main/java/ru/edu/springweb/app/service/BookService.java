package ru.edu.springweb.app.service;

import ru.edu.springweb.in.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAll();
    public Book getBookById(int id);

    public Book addBook(Book book);

    public Book updateBook(int id, Book updatedBook);

    public void deleteBook(int id);
}
