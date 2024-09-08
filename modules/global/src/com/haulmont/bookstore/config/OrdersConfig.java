package com.haulmont.bookstore.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;

@Source(type = SourceType.DATABASE)
public interface OrdersConfig extends Config {
    @Property("cuba.maxCountOrderLines")
    @Default("4")
    int getMaxCountOrderLines();
}