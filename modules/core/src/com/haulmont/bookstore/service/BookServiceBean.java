package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.Book;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(BookService.NAME)
public class BookServiceBean implements BookService {
    private final DataManager dataManager;

    @Inject
    public BookServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public List<Book> getRandomBooks(int n) {
        return dataManager.load(Book.class)
                .query("select e from bookstore_Book e " +
                        "where e.isAvailable = true order by function('RANDOM')")
                .maxResults(n)
                .list();
    }

    public List<Book> getAvailableBooks() {
        return dataManager.load(Book.class)
                .query("select e from bookstore_Book e where e.isAvailable = true")
                .maxResults(4)
                .list();
    }
}