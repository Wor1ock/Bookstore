package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.OrderLine;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(OrderLineService.NAME)
public class OrderLineServiceBean implements OrderLineService {
    private final DataManager dataManager;

    @Inject
    public OrderLineServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public OrderLine create() {
        return dataManager.create(OrderLine.class);
    }
}