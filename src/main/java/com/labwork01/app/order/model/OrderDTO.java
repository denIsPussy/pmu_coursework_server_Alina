package com.labwork01.app.order.model;

import com.labwork01.app.bouquet.model.BouquetDTO;
import com.labwork01.app.user.model.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDTO {
    private Long id;
    private String date;
    private Integer sum;
    private List<BouquetDTO> bouquets;
    private UserDTO user;

    public OrderDTO(){

    }
    public OrderDTO(Order order){
        id = order.getId();
        date = order.getDate();
        sum = order.getSum();
        bouquets = order.getBouquets() != null? order.getBouquets().stream().map(BouquetDTO::new).collect(Collectors.toList()) : null;
        user = new UserDTO(order.getUser());
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

    public List<BouquetDTO> getBouquets() {
        return bouquets;
    }

    public void setBouquets(List<BouquetDTO> bouquets) {
        this.bouquets = bouquets;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
