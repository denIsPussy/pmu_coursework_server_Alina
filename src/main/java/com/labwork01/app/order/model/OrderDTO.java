package com.labwork01.app.order.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDTO {
    private long id;
    private String dateCreate;
    private int sum;
    private List<OrderFlowerDTO> orderFlower;
    public OrderDTO(){

    }
    public OrderDTO(Order order){
        this.id = order.getId();
        this.dateCreate = order.getDateCreate();
        this.sum = order.getSum();
        this.orderFlower = order.getFlowers().stream().map(OrderFlowerDTO::new).toList();
    }
    public long getId() { return id; }
    public String getDateCreate(){
        return dateCreate;
    }
    public int getSum(){ return sum; }
    public List<OrderFlowerDTO> getOrderFlower(){
        return orderFlower;
    }
}
