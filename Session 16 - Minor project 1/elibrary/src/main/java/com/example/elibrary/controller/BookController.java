package com.example.elibrary.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.elibrary.models.Book;
import com.example.elibrary.models.enums.BookFilterType;
import com.example.elibrary.models.request.BookCreateRequest;
import com.example.elibrary.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity saveBook(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
        return new ResponseEntity(bookService.saveBook(bookCreateRequest), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("filterType") BookFilterType bookFilterType,
                                  @RequestParam("filterValue") String filterValue){
        return bookService.findBooksByFilter(bookFilterType, filterValue);
    }

    // Add rest of the API's for book - update-book, delete-book, get-book-by-id etc.
}
