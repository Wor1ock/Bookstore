package com.haulmont.bookstore.core.group;

import com.haulmont.bookstore.entity.OnlineOrder;
import com.haulmont.bookstore.entity.Status;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.Constraint;
import com.haulmont.cuba.security.entity.EntityOp;

@AccessGroup(name = "Root")
public class RootGroup extends AnnotatedAccessGroupDefinition {

    @Constraint(operations = {EntityOp.UPDATE, EntityOp.DELETE})
    public boolean customerConstraints(OnlineOrder onlineOrder) {
        return onlineOrder.getStatus().compareTo(Status.CONFIRMED) != 0;
    }
}
