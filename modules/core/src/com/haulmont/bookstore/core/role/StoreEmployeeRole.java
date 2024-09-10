package com.haulmont.bookstore.core.role;

import com.haulmont.bookstore.entity.*;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.*;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.*;

@Role(name = StoreEmployeeRole.NAME, description = "Сотрудник магазина")
public class StoreEmployeeRole extends AnnotatedRoleDefinition {
    public final static String NAME = "StoreEmployeeRole";

    @ScreenAccess(screenIds = {"application-bookstore", "bookstore_Book.browse", "bookstore_Author.browse", "bookstore_OnlineOrder.browse", "bookstore_OnlineOrder.edit", "bookstore_Customer.browse", "bookstore_Author.edit", "bookstore_Book.edit", "bookstore_Customer.edit"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = OnlineOrder.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = OrderLine.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Customer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Address.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Book.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Author.class, operations = EntityOp.READ)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = OrderLine.class, view = "*")
    @EntityAttributeAccess(entityClass = OnlineOrder.class, modify = "*")
    @EntityAttributeAccess(entityClass = Customer.class, modify = "*")
    @EntityAttributeAccess(entityClass = Address.class, modify = "*")
    @EntityAttributeAccess(entityClass = Book.class, view = "*")
    @EntityAttributeAccess(entityClass = Author.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @SpecificAccess(permissions = "allow-confirmOnlineOrder")
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }

    @ScreenComponentAccess(screenId = "bookstore_Author.browse", deny = "generateReportBtn")
    @ScreenComponentAccess(screenId = "bookstore_OnlineOrder.edit", deny = {"customerField<lookup>", "customerField<clear>"}, modify = "confirmBtn")
    @Override
    public ScreenComponentPermissionsContainer screenComponentPermissions() {
        return super.screenComponentPermissions();
    }
}
