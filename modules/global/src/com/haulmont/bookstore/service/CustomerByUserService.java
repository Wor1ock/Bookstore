package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.Customer;
import com.haulmont.cuba.security.entity.User;

import java.util.List;

public interface CustomerByUserService {
    String NAME = "bookstore_CustomerByUserService";

    List<Customer> getCustomersByUser(User user, int quantity);
}