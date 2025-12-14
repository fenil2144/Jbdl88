package com.example.elibrary.service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.elibrary.models.Author;
import com.example.elibrary.models.Book;
import com.example.elibrary.models.enums.BookFilterType;
import com.example.elibrary.models.request.BookCreateRequest;
import com.example.elibrary.repository.AuthorRepositoryInterf;
import com.example.elibrary.repository.BookRepositoryInterf;

@Service
@Transactional
public class BookService {

    @Autowired
    BookRepositoryInterf bookRepositoryInterf;

    @Autowired
    AuthorRepositoryInterf authorRepositoryInterf;

    public Book saveBook(BookCreateRequest bookCreateRequest) {
        Book book = bookCreateRequest.toBook();
        Author author = book.getAuthor();

        // Logic to check if author already exists
        Author authorFromDb = authorRepositoryInterf.findByEmail(author.getEmail());
        if(authorFromDb == null){
            authorFromDb = authorRepositoryInterf.save(author);
        }
        // Setting the object received from database as it contains id
        book.setAuthor(authorFromDb);
        return bookRepositoryInterf.save(book);
    }

    public List<Book> findBooksByFilter(BookFilterType bookFilterType, String filterValue) {

        switch (bookFilterType) {
            case TITLE -> {
                return bookRepositoryInterf.findByTitle(filterValue);
            }
            case ID -> {
                return bookRepositoryInterf.findAllById(List.of(Integer.parseInt(filterValue)));
            }
            case AUTHOR_NAME -> {
                return bookRepositoryInterf.findByAuthor_name(filterValue);
            }
            case COST -> {
                return bookRepositoryInterf.findByCost(Integer.parseInt(filterValue));
            }
            case GENRE -> {
                return bookRepositoryInterf.findByGenre(filterValue);
            }
        }
        return null;
    }

    public Optional<Book> findBookById(int bookId){
        return bookRepositoryInterf.findById(bookId);
    }

    public Book save(Book book){
        return bookRepositoryInterf.save(book);
    }
}
