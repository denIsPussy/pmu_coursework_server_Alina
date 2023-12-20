package com.labwork01.app.order.model;

import com.labwork01.app.bouquet.model.Bouquet;
import com.labwork01.app.user.model.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private Integer sum;
    @ManyToMany()
    @JoinTable(
            name = "order_bouquet",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "bouquet_id")
    )
    private List<Bouquet> bouquets = new ArrayList<>();
    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;

    public Order() {
    }

    public Order(String date, Integer sum, List<Bouquet> bouquets, User user) {
        this.date = date;
        this.sum = sum;
        this.bouquets = bouquets;
        this.user = user;
    }

    public Order(OrderDTO orderDTO) {
        this.id = orderDTO.getId();
        this.date = orderDTO.getDate();
        this.sum = orderDTO.getSum();
        this.user = new User(orderDTO.getUser());
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public List<Bouquet> getBouquets() {
        return bouquets;
    }

    public void setBouquets(List<Bouquet> bouquets) {
        this.bouquets = bouquets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUser(User user) {
        this.user = user;
        user.addOrder(this);
    }

    public void removeUser() {
        user.removeOrder(this);
        this.user = null;
    }

    public void addBouquet(Bouquet bouquet) {
        bouquets.add(bouquet);
        bouquet.getOrders().add(this);
    }

    public void removeBouquet(Bouquet bouquet) {
        bouquets.remove(bouquet);
        bouquet.getOrders().remove(this);
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                ", bouquets=" + bouquets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date) && Objects.equals(sum, order.sum) && Objects.equals(bouquets, order.bouquets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, sum, bouquets);
    }
}
