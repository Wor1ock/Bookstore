package com.haulmont.bookstore.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "bookstore_ExtendedUser")
@Extends(User.class)
public class ExtendedUser extends User {
    @JoinColumn(name = "CUSTOMER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}