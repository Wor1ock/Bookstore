package com.haulmont.bookstore.web.screens.customer;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.bookstore.entity.Customer;

@UiController("bookstore_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
@LoadDataBeforeShow
public class CustomerEdit extends StandardEditor<Customer> {
}