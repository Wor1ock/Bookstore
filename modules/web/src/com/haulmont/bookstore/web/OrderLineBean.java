package com.haulmont.bookstore.web;

import com.haulmont.bookstore.entity.Book;
import com.haulmont.bookstore.entity.OnlineOrder;
import com.haulmont.bookstore.entity.OrderLine;
import com.haulmont.bookstore.service.RandAvblBookService;
import com.haulmont.bookstore.service.OrderLineService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component(OrderLineBean.NAME)
public class OrderLineBean {
    public static final String NAME = "bookstore_RandUniqOrderLineBean";
    private final OrderLineService orderLineService;
    private final RandAvblBookService randAvblBookService;

    @Inject
    public OrderLineBean(OrderLineService orderLineService, RandAvblBookService randAvblBookService) {
        this.orderLineService = orderLineService;
        this.randAvblBookService = randAvblBookService;
    }

    public List<OrderLine> createOrderLines(List<OrderLine> orderLinesInOrder, OnlineOrder onlineOrder, int quantity) {
        List<Book> booksInOrder = orderLinesInOrder.stream().map(OrderLine::getBook)
                .collect(Collectors.toList());
        List<Book> books = randAvblBookService.getBooks(booksInOrder, quantity);
        List<OrderLine> orderLines = new ArrayList<>();
        for (Book book : books) {
            orderLines.add(createOrderLineByBook(book, onlineOrder));
        }

        return orderLines;
    }

    private OrderLine createOrderLineByBook(Book book, OnlineOrder onlineOrder) {
        OrderLine orderLine = orderLineService.create();
        orderLine.setBook(book);
        orderLine.setBookTitle(book.getTitle());
        orderLine.setBookPrice(book.getPrice());
        orderLine.setQuantity(1);
        orderLine.setOnlineOrder(onlineOrder);

        return orderLine;
    }
}