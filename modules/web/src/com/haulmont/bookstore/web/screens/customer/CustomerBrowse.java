package com.haulmont.bookstore.web.screens.customer;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.bookstore.entity.Customer;

@UiController("bookstore_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
@LoadDataBeforeShow
public class CustomerBrowse extends StandardLookup<Customer> {
}