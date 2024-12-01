package ru.edu.springweb.app.mapper;

import ru.edu.springweb.app.model.BookDto;
import ru.edu.springweb.in.model.Book;
import ru.edu.springweb.out.entity.BookEntity;

import java.util.List;

public interface BookMapper {
    BookDto map(Book book);

    Book map(BookDto book);

    BookEntity to(Book book);

    Book to(BookEntity book);

    List<BookDto> map(List<Book> books);

    List<Book> to(List<BookEntity> books);
}
