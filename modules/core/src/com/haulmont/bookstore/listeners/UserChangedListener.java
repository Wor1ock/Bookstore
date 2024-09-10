package com.haulmont.bookstore.listeners;

import com.haulmont.bookstore.entity.Customer;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;

import java.util.UUID;

import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;

@Component("bookstore_UserChangedListener")
public class UserChangedListener {
    private final DataManager dataManager;

    @Inject
    public UserChangedListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onUserAfterCommit(EntityChangedEvent<User, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            Customer customer = dataManager.create(Customer.class);
            User user = dataManager.load(User.class).id(event.getEntityId().getValue()).one();
            customer.setUser(user);
            customer.setFullName(user.getName());
            dataManager.commit(customer);
        }
    }
}