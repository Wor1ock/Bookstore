package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.Book;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(AvailableBookService.NAME)
public class AvailableBookServiceBean implements AvailableBookService {
    private final DataManager dataManager;

    @Inject
    public AvailableBookServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Book> getAllBooks() {
        return dataManager.load(Book.class)
                .query("select e from bookstore_Book e where e.isAvailable = true")
                .list();
    }
}