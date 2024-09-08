package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.Book;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(RandAvblBookService.NAME)
public class RandAvblBookServiceBean implements RandAvblBookService {
    private final DataManager dataManager;

    @Inject
    public RandAvblBookServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Book> getBooks(List<Book> excludedBooks, int quantity) {
        return dataManager.load(Book.class)
                .query("select e from bookstore_Book e " +
                        "where e.isAvailable = true " +
                        "and e not in :excludedBooks order by function('RANDOM')")
                .parameter("excludedBooks", excludedBooks)
                .maxResults(quantity)
                .list();
    }
}