package com.haulmont.bookstore.web.screens.book;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.bookstore.entity.Book;

@UiController("bookstore_Book.browse")
@UiDescriptor("book-browse.xml")
@LookupComponent("booksTable")
@LoadDataBeforeShow
public class BookBrowse extends StandardLookup<Book> {
}