package com.haulmont.bookstore.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "BOOKSTORE_ORDER_LINE")
@Entity(name = "bookstore_OrderLine")
public class OrderLine extends StandardEntity {
    private static final long serialVersionUID = -5977329779703116070L;

    @Column(name = "QUANTITY")
    @NotNull
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "BOOK_TITLE", length = 250)
    @NotNull
    private String bookTitle;

    @Column(name = "BOOK_PRICE", precision = 10, scale = 2)
    private BigDecimal bookPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ONLINE_ORDER_ID")
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