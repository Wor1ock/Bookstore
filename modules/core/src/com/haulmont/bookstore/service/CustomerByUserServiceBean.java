package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.Customer;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(CustomerByUserService.NAME)
public class CustomerByUserServiceBean implements CustomerByUserService {
    private final DataManager dataManager;

    @Inject
    public CustomerByUserServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public List<Customer> getCustomersByUser(User user) {
        return dataManager.load(Customer.class)
                .query("select b from bookstore_Customer b where b.user = :user")
                .parameter("user", user)
                .list();
    }
}