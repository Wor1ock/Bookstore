package com.haulmont.bookstore.service;

import com.haulmont.cuba.security.entity.User;

import java.util.List;

public interface UserService {

    String NAME = "bookstore_UserService";

    /**
     * Находит всех сотрудников магазина, имеющих определённую роль.
     * Сотрудники магазина — это пользователи, у которых назначена роль-маркер для работы с заказами.
     *
     * @return список сотрудников магазина (пользователей системы).
     */
    List<User> findStoreEmployees();
}
