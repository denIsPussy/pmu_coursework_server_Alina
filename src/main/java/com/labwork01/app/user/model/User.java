package com.labwork01.app.user.model;

import com.labwork01.app.order.model.Order;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String dateOfBirth;
    @Column(nullable = true)
    private String phoneNumber;
    @Column(nullable = true)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public User() {
    }

    public User(String userName, String dateOfBirth, String phoneNumber, String password) {
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(UserDTO userDTO) {
        this.userName = userDTO.getUserName();
        this.dateOfBirth = userDTO.getDateOfBirth();
        this.password = userDTO.getPassword();
        this.phoneNumber = userDTO.getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order){
        orders.remove(order);
        order.removeUser();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(password, user.password) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, dateOfBirth, phoneNumber, password, orders);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", orders=" + orders +
                '}';
    }
}
