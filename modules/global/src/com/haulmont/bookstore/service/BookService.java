package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    String NAME = "bookstore_BookService";

    List<Book> getRandomBooks(int n);

    List<Book> getAvailableBooks();
}