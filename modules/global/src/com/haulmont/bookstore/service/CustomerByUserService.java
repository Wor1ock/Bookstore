package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.Customer;
import com.haulmont.cuba.security.entity.User;

public interface CustomerByUserService {
    String NAME = "bookstore_CustomerByUserService";

    Customer getCustomerByUser(User user);
}