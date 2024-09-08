package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.Book;

import java.util.List;

public interface RandAvblBookService {
    String NAME = "bookstore_BookService";

    List<Book> getBooks(List<Book> excludedBooks, int maxResult);
}