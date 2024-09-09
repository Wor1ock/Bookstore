package com.haulmont.bookstore.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.Entity;

@PublishEntityChangedEvents
@Entity(name = "bookstore_ExtUser")
@Extends(User.class)
public class ExtUser extends User {
    private static final long serialVersionUID = 1490682228206327654L;
}