package com.labwork01.app.order.model;

import jakarta.persistence.*;

import java.util.*;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String DateCreate;
    @Column(nullable = true)
    private int Sum;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderFlower> orderFlower = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDateCreate() {
        return DateCreate;
    }
    public void setDateCreate(String DateCreate) {
        this.DateCreate = DateCreate;
    }

    public int getSum() {
        return Sum;
    }

    public List<OrderFlower> getFlowers() {
        return orderFlower;
    }

    public void addFlower(OrderFlower orderFlower) {
        this.orderFlower.add(orderFlower);
        Sum += orderFlower.getFlower().getPrice() * orderFlower.getQuantity();
    }
    public void removeFlower(OrderFlower orderFlower) {
        this.orderFlower.remove(orderFlower);
        Sum -= orderFlower.getFlower().getPrice() * orderFlower.getQuantity();
    }

    public Order() {
    }
    public Order(OrderDTO orderDTO) {
        this.DateCreate = orderDTO.getDateCreate();
        this.Sum = orderDTO.getSum();
    }
    public Order(String DateCreate) {
        this.DateCreate = DateCreate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order: Id: " + id + ". DateCreate: " + DateCreate + ". Sum: " + Sum + ".";
    }
}
