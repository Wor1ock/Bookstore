package com.haulmont.bookstore.core.role;

import com.haulmont.bookstore.entity.*;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.*;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenComponentPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = CustomerRole.NAME, description = "Покупатель")
public class CustomerRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Customer";

    @ScreenAccess(screenIds = {"application-bookstore", "bookstore_Author.browse", "bookstore_Book.browse", "bookstore_OnlineOrder.browse", "bookstore_OnlineOrder.edit", "bookstore_Customer.browse"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Customer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = OrderLine.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = OnlineOrder.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Book.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Author.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Address.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Customer.class, view = "*")
    @EntityAttributeAccess(entityClass = OrderLine.class, modify = "*")
    @EntityAttributeAccess(entityClass = OnlineOrder.class, modify = "*")
    @EntityAttributeAccess(entityClass = Book.class, view = "*")
    @EntityAttributeAccess(entityClass = Author.class, view = "*")
    @EntityAttributeAccess(entityClass = Address.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @ScreenComponentAccess(screenId = "bookstore_OnlineOrder.edit", deny = "confirmBtn")
    @ScreenComponentAccess(screenId = "bookstore_Author.browse", deny = "generateReportBtn")
    @ScreenComponentAccess(screenId = "bookstore_OnlineOrder.edit", deny = "confirmBtn")
    @Override
    public ScreenComponentPermissionsContainer screenComponentPermissions() {
        return super.screenComponentPermissions();
    }
}
