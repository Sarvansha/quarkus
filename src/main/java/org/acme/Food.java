package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Food extends PanacheEntity {

    @Column(length = 40)
    public String name;

    @Column(length = 40)
    public String restaurantName;

    public double price;

}