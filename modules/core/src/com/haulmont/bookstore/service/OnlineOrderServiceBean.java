package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.OnlineOrder;
import com.haulmont.bookstore.entity.Status;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service(OnlineOrderService.NAME)
public class OnlineOrderServiceBean implements OnlineOrderService {
    @Inject
    private DataManager dataManager;
    @Inject
    private UserService userService;
    @Inject
    private EmailService emailService;
    @Inject
    private Logger log;

    public void checkUnprocessedOrders(Integer delay) {
        List<OnlineOrder> unprocessedOrders = findUnprocessedOrders(delay);
        List<User> storeEmployees = userService.findStoreEmployees();
        if (unprocessedOrders.isEmpty() || storeEmployees.isEmpty()) {
            log.info("No unprocessed orders or store employees for notifications");
            return;
        }

        for (OnlineOrder order : unprocessedOrders) {
            for (User employee : storeEmployees) {
                sendEmailNotification(employee.getEmail(), order);
            }
        }
    }

    public void sendEmailNotification(String email, OnlineOrder order) {
        String subject = "Уведомление о необработанном заказе";
        String body = "Заказ №" + order.getId() + " не был обработан в течение установленного времени.";

        EmailInfo emailInfo = new EmailInfo(
                email,
                subject,
                body
        );

        emailService.sendEmailAsync(emailInfo);
    }

    public List<OnlineOrder> findUnprocessedOrders(Integer notificationDelay) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thresholdTime = now.minusMinutes(notificationDelay);

        return dataManager.load(OnlineOrder.class)
                .query("SELECT o FROM bookstore_OnlineOrder o WHERE o.status=:initialStatus AND o.createTs<=:thresholdTime")
                .parameter("initialStatus", Status.NEW)
                .parameter("thresholdTime", Timestamp.valueOf(thresholdTime))
                .list();
    }
}
