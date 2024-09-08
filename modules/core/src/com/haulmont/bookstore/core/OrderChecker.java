package com.haulmont.bookstore.core;

import com.haulmont.bookstore.config.MyAppConfig;
import com.haulmont.bookstore.service.OnlineOrderService;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component(OrderChecker.NAME)
public class OrderChecker {
    public static final String NAME = "bookstore_OrderCheckTask";
    @Inject
    private OnlineOrderService onlineOrderService;
    @Inject
    private MyAppConfig myAppConfig;
    @Inject
    private Logger log;

    @Scheduled(fixedRate = 60000)
    public void checkOrders() {
        Integer delay = myAppConfig.getNotificationDelay();
        log.info("Проверка заказов. Время задержки: {} минут", delay);
        onlineOrderService.checkUnprocessedOrders(delay);
    }
}