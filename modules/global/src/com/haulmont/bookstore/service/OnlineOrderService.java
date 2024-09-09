package com.haulmont.bookstore.service;

import com.haulmont.bookstore.entity.OnlineOrder;

import java.util.List;

public interface OnlineOrderService {

    String NAME = "bookstore_OnlineOrderService";

    /**
     * Находит все необработанные заказы, которые находятся в начальном состоянии
     * (например, со статусом "NEW") и не были обработаны в течение заданного времени.
     *
     * @param notificationDelay количество минут, прошедших с момента создания заказа,
     *                          по истечении которых он считается необработанным.
     * @return список необработанных заказов.
     */
    List<OnlineOrder> findUnprocessedOrders(Integer notificationDelay);

    /**
     * Проверяет все необработанные заказы, которые находятся в начальном состоянии,
     * и отправляет уведомления сотрудникам магазина, если заказ находится в таком
     * состоянии дольше указанного времени.
     *
     * @param delay количество минут, прошедших с момента создания заказа, после
     *              которых заказ считается необработанным.
     */
    void checkUnprocessedOrders(Integer delay);

    /**
     * Отправляет email-уведомление о необработанном заказе на указанный электронный адрес.
     *
     * @param email электронный адрес сотрудника, которому отправляется уведомление.
     * @param order объект заказа, по которому нужно отправить уведомление.
     */
    void sendEmailNotification(String email, OnlineOrder order);
}