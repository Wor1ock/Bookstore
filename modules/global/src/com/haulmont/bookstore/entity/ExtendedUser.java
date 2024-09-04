package com.haulmont.bookstore.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "bookstore_ExtendedUser")
@Extends(User.class)
public class ExtendedUser extends User {
    @Transient
    @MetaProperty
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}