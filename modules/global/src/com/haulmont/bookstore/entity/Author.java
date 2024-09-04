package com.haulmont.bookstore.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "BOOKSTORE_AUTHOR")
@Entity(name = "bookstore_Author")
public class Author extends StandardEntity {
    private static final long serialVersionUID = 2496840374188864712L;

    @NotNull
    @Column(name = "FULL_NAME", nullable = false, length = 450)
    private String fullName;

    @Column(name = "EMAIL", unique = true)
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$ ")
    private String email;

    @JoinTable(name = "BOOKSTORE_BOOK_AUTHOR_LINK",
            joinColumns = @JoinColumn(name = "AUTHOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    @OnDelete(DeletePolicy.UNLINK)
    @ManyToMany
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}