package com.haulmont.bookstore.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Table(name = "BOOKSTORE_BOOK")
@Entity(name = "bookstore_Book")
@NamePattern("%s|title")
public class Book extends StandardEntity {
    private static final long serialVersionUID = 3708162790511649904L;

    @Column(name = "TITLE", length = 250, nullable = false)
    @NotNull
    private String title;

    @JoinTable(name = "BOOKSTORE_BOOK_AUTHOR_LINK",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    @OnDelete(DeletePolicy.UNLINK)
    @ManyToMany
    private List<Author> authors;

    @Column(name = "PRICE", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "IS_AVAILABLE")
    private Boolean isAvailable = true;

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}