package com.haulmont.bookstore.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "BOOKSTORE_CUSTOMER")
@Entity(name = "bookstore_Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = -1467985972714221375L;

    @Column(name = "FULL_NAME", nullable = false, length = 450)
    @NotNull
    private String fullName;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "customer")
    private List<OnlineOrder> onlineOrders;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    private ExtendedUser extendedUser;

    public List<OnlineOrder> getOnlineOrders() {
        return onlineOrders;
    }

    public void setOnlineOrders(List<OnlineOrder> onlineOrders) {
        this.onlineOrders = onlineOrders;
    }

    public ExtendedUser getExtendedUser() {
        return extendedUser;
    }

    public void setExtendedUser(ExtendedUser extendedUser) {
        this.extendedUser = extendedUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}