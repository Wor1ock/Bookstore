package com.haulmont.bookstore.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Status implements EnumClass<String> {

    NEW("New"),
    CONFIRMED("Confirmed");

    private String id;

    Status(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Status fromId(String id) {
        for (Status at : Status.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}