package ru.edu.springweb.app.mapper;

import org.springframework.stereotype.Component;
import ru.edu.springweb.app.model.BookDto;
import ru.edu.springweb.in.model.Book;
import ru.edu.springweb.out.entity.BookEntity;

import java.util.List;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public BookDto map(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDto(book.author(), book.title());
    }

    @Override
    public Book map(BookDto book) {
        if (book == null) {
            return null;
        }
        return new Book(book.author(), book.title());
    }

    @Override
    public BookEntity to(Book book) {
        if (book == null) {
            return null;
        }
        return new BookEntity(book.author(), book.title());
    }

    @Override
    public Book to(BookEntity book) {
        if (book == null) {
            return null;
        }
        return new Book(book.getAuthor(), book.getTitle());
    }

    @Override
    public List<BookDto> map(List<Book> books) {
        if (books == null) {
            return null;
        }
        return books.stream().map(this::map).toList();
    }

    @Override
    public List<Book> to(List<BookEntity> books) {
        if (books == null) {
            return null;
        }
        return books.stream().map(this::to).toList();
    }
}
