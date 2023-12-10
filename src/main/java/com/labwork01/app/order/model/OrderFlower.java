package com.labwork01.app.order.model;

import com.labwork01.app.flower.model.Flower;
import jakarta.persistence.*;
import java.util.*;
import java.util.Objects;

@Entity(name = "order_flower")
public class OrderFlower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @Column(nullable = false)
    private int quantity;

    public OrderFlower(){

    }
    public OrderFlower(Order order, Flower flower, int quantity){
        this.order = order;
        this.flower = flower;
        this.quantity = quantity;
    }
    public OrderFlower(OrderFlowerDTO orderFLowerDTO){
        this.order = order;
        this.flower = flower;
        this.quantity = quantity;
    }

    public long getId(){ return id;}

    public Flower getFlower(){ return flower;}
    public void setFlower(Flower flower){ this.flower = flower; }

    public Order getOrder(){ return order;}
    public void setOrder(Order order){ this.order = order; }

    public int getQuantity(){ return quantity;}
    public void setQuantity(int quantity){ this.quantity = quantity; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFlower OrderFlower = (OrderFlower) o;
        return Objects.equals(id, OrderFlower.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderFlower. " + "Id: " + id + ", Order: " + order.getId() + ", Flower: " + flower.getId() +  ", Количество: " + quantity + ".";
    }
}
