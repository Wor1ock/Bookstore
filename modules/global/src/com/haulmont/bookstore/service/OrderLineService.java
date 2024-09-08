package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.OrderLine;

public interface OrderLineService {
    String NAME = "bookstore_OrderLineService";

    OrderLine create();
}