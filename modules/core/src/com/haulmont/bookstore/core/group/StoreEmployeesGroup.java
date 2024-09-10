package com.haulmont.bookstore.core.group;

import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;

@AccessGroup(name = "Store Employees", parent = RootGroup.class)
public class StoreEmployeesGroup extends AnnotatedAccessGroupDefinition {
}
