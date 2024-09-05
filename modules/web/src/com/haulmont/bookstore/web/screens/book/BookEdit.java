package com.haulmont.bookstore.web.screens.book;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.bookstore.entity.Book;

@UiController("bookstore_Book.edit")
@UiDescriptor("book-edit.xml")
@EditedEntityContainer("bookDc")
@LoadDataBeforeShow
public class BookEdit extends StandardEditor<Book> {
}