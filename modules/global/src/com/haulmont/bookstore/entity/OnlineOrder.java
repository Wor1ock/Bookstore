package com.haulmont.bookstore.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Table(name = "BOOKSTORE_ONLINE_ORDER")
@Entity(name = "bookstore_OnlineOrder")
public class OnlineOrder extends StandardEntity {
    private static final long serialVersionUID = 7439232266161470152L;

    @Column(name = "STATUS")
    private String status;

    @Transient
    @MetaProperty
    private BigDecimal totalCost;

    @OneToMany(mappedBy = "onlineOrder")
    private List<OrderLine> orderLines;
    @OnDelete(DeletePolicy.UNLINK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public BigDecimal getTotalCost() {
        return orderLines.stream()
                .map(orderLine -> orderLine.getBookPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Status getStatus() {
        return status == null ? null : Status.fromId(status);
    }

    public void setStatus(Status status) {
        this.status = status == null ? null : status.getId();
    }
}