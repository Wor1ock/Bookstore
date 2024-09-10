package com.haulmont.bookstore.core.group;

import com.haulmont.bookstore.entity.OnlineOrder;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.group.ConstraintsContainer;

@AccessGroup(name = "Customers", parent = RootGroup.class)
public class CustomerGroup extends AnnotatedAccessGroupDefinition {

    @JpqlConstraint(target = OnlineOrder.class, where = "{E}.createdBy = :session$userLogin")
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }

}
