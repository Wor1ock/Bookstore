package com.haulmont.bookstore.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.cuba.core.entity.EmbeddableEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@MetaClass(name = "bookstore_Address")
@Embeddable
public class Address extends EmbeddableEntity {
    private static final long serialVersionUID = 836218597002479049L;

    @Column(name = "CITY", length = 100)
    @NotNull
    private String city;

    @Column(name = "STREET", length = 100)
    @NotNull
    private String street;

    @Column(name = "BUILDING", length = 5)
    @NotNull
    private String building;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}