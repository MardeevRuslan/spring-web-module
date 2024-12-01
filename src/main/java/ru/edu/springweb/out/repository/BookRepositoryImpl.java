package ru.edu.springweb.out.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.edu.springweb.app.mapper.BookMapper;
import ru.edu.springweb.in.model.Book;
import ru.edu.springweb.out.entity.BookEntity;
import ru.edu.springweb.out.exception.EntityNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private final BookMapper mapper;

    private final List<BookEntity> books;
    private int currentId;

    public BookRepositoryImpl(BookMapper mapper) {
        this.mapper = mapper;
        books = new ArrayList<>();
        currentId = 0;
    }

    @PostConstruct
    public void init() {
        books.add(new BookEntity(currentId, "Author1", "Title1"));
        books.add(new BookEntity(currentId++, "Author2", "Title2"));
        books.add(new BookEntity(currentId++, "Author3", "Title3"));
        books.add(new BookEntity(currentId++, "Author4", "Title4"));
    }

    @Override
    public List<Book> getAll() {
        return mapper.to(books);
    }

    @Override
    public Book getBookById(int id) {
        var book = getById(id);
        return mapper.to(book.orElseThrow(() -> new EntityNotFound("Not found book id = " + id)));
    }

    @Override
    public Book addBook(Book book) {
        var entity = mapper.to(book);
        entity.setId(currentId++);
        books.add(entity);
        return mapper.to(entity);
    }

    @Override
    public Book updateBook(int id, Book updatedBook) {
        var entity = getById(id).orElseThrow(() -> new EntityNotFound("Not found book id = " + id));
        entity.setTitle(updatedBook.title());
        entity.setAuthor(updatedBook.author());
        books.add(entity);
        return mapper.to(entity);
    }

    @Override
    public void deleteBook(int id) {
        if (!books.removeIf(b -> b.getId() == id)) {
            throw new EntityNotFound("Not found book id = " + id);
        }
    }

    private Optional<BookEntity> getById(int id) {
        return books.stream().
                filter(b -> b.getId() == id).
                findFirst();
    }
}
