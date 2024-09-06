package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.Book;

import java.util.List;

public interface AvailableBookService {
    String NAME = "bookstore_BookService";

    List<Book> getAllBooks();
}