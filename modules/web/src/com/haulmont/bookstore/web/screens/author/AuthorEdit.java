package com.haulmont.bookstore.web.screens.author;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.bookstore.entity.Author;

@UiController("bookstore_Author.edit")
@UiDescriptor("author-edit.xml")
@EditedEntityContainer("authorDc")
@LoadDataBeforeShow
public class AuthorEdit extends StandardEditor<Author> {
}