package com.labwork01.app.user.model;

import com.labwork01.app.order.model.OrderDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private Long id;
    private String userName;
    private String dateOfBirth;
    private String phoneNumber;
    private String password;
    private List<OrderDTO> orders;
    public UserDTO(){

    }
    public UserDTO(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.dateOfBirth = user.getDateOfBirth();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.orders = user.getOrders() != null ? user.getOrders().stream().map(OrderDTO::new).collect(Collectors.toList()) : null;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
    public List<OrderDTO> getOrders() {
        return orders;
    }
}
