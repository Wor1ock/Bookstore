package com.haulmont.bookstore.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;

@Source(type = SourceType.DATABASE)
public interface MyAppConfig extends Config {
    @Property("app.notification.delay")
    Integer getNotificationDelay();
}
