package com.haulmont.bookstore.core;

import com.haulmont.bookstore.service.OnlineOrderService;

public interface OrderChecker {
    /**
     * Выполняет проверку необработанных заказов.
     * Метод использует задержку, заданную в конфигурации приложения, и
     * инициирует проверку через сервис {@link OnlineOrderService}.
     * Если заказы не были обработаны в течение указанного времени,
     * отправляются уведомления сотрудникам магазина.
     */
    void checkOrders();
}
