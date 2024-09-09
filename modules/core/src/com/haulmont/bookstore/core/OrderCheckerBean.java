package com.haulmont.bookstore.core;

import com.haulmont.bookstore.config.MyAppConfig;
import com.haulmont.bookstore.service.OnlineOrderService;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component(OrderCheckerBean.NAME)
public class OrderCheckerBean implements OrderChecker {
    public static final String NAME = "bookstore_OrderCheckerBean";
    @Inject
    private OnlineOrderService onlineOrderService;
    @Inject
    private MyAppConfig myAppConfig;
    @Inject
    private Logger log;

    public void checkOrders() {
        Integer delay = myAppConfig.getNotificationDelay();
        log.info("Checking orders. Notification delay: {} minutes", delay);
        onlineOrderService.checkUnprocessedOrders(delay);
    }
}