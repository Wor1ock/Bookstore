package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.Customer;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(CustomerByUserService.NAME)
public class CustomerByUserServiceBean implements CustomerByUserService {
    private final DataManager dataManager;

    @Inject
    public CustomerByUserServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public Customer getCustomerByUser(User user) {

        Customer customer = dataManager.load(Customer.class)
                .query("select b from bookstore_Customer b where b.user = :user")
                .parameter("user", user)
                .optional()
                .orElse(null);
        if (customer == null) {
            customer = dataManager.create(Customer.class);
            customer.setUser(user);
            customer.setFullName(user.getName());
            dataManager.commit(customer);
        }
        return customer;
    }
}