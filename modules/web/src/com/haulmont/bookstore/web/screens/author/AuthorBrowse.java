package com.haulmont.bookstore.web.screens.author;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.bookstore.entity.Author;

@UiController("bookstore_Author.browse")
@UiDescriptor("author-browse.xml")
@LookupComponent("authorsTable")
@LoadDataBeforeShow
public class AuthorBrowse extends StandardLookup<Author> {
}