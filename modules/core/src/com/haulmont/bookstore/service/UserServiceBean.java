package com.haulmont.bookstore.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(UserService.NAME)
public class UserServiceBean implements UserService {
    private static final String MANAGER_ROLE_NAME = "StoreEmployeeRole";
    @Inject
    private DataManager dataManager;

    public List<User> findStoreEmployees() {
        List<User> managers = dataManager.load(User.class)
                .query("SELECT u FROM sec$User u JOIN u.userRoles ur WHERE ur.roleName=:roleName")
                .parameter("roleName", MANAGER_ROLE_NAME)
                .list();
        return managers;
    }
}