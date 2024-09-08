package com.haulmont.bookstore.core.role;

import com.haulmont.bookstore.entity.Author;
import com.haulmont.bookstore.entity.Book;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.*;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.*;
import com.haulmont.reports.entity.Report;

@Role(name = DirectoryEditorRole.NAME, description = "Редактор справочников")
public class DirectoryEditorRole extends AnnotatedRoleDefinition {
    public final static String NAME = "DirectoryEditorRole";

    @ScreenAccess(screenIds = {"application-bookstore", "bookstore_Book.browse", "bookstore_Author.browse", "bookstore_Author.edit", "bookstore_Book.edit"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = FileDescriptor.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Report.class, operations = {EntityOp.CREATE, EntityOp.READ})
    @EntityAccess(entityClass = Author.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Book.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = FileDescriptor.class, modify = "*")
    @EntityAttributeAccess(entityClass = Report.class, modify = "*")
    @EntityAttributeAccess(entityClass = Author.class, modify = "*")
    @EntityAttributeAccess(entityClass = Book.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @SpecificAccess(permissions = {"generateBooksByAuthorReport", "generateReportBtn", "allow-generateBooksByAuthorReport"})
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }

    @ScreenComponentAccess(screenId = "bookstore_Author.browse", view = "generateReportBtn")
    @Override
    public ScreenComponentPermissionsContainer screenComponentPermissions() {
        return super.screenComponentPermissions();
    }
}
