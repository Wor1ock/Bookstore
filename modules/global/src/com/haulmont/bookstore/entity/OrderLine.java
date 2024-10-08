package com.haulmont.bookstore.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Table(name = "BOOKSTORE_ORDER_LINE")
@Entity(name = "bookstore_OrderLine")
public class OrderLine extends StandardEntity {
    private static final long serialVersionUID = -5977329779703116070L;

    @Column(name = "QUANTITY", nullable = false)
    @NotNull
    @Positive
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "BOOK_TITLE", length = 250, nullable = false)
    @NotNull
    private String bookTitle;

    @Column(name = "BOOK_PRICE", precision = 10, scale = 2)
    private BigDecimal bookPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ONLINE_ORDER_ID")
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @NotNull
    private OnlineOrder onlineOrder;

    public OnlineOrder getOnlineOrder() {
        return onlineOrder;
    }

    public void setOnlineOrder(OnlineOrder onlineOrder) {
        this.onlineOrder = onlineOrder;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}