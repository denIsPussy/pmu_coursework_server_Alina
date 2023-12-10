package com.labwork01.app.order.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderFlowerDTO {
    private long orderId;
    private long flowerId;
    private int quantity;
    public OrderFlowerDTO(){

    }
    public OrderFlowerDTO(OrderFlower orderFlower){
        this.orderId = orderFlower.getOrder().getId();
        this.flowerId = orderFlower.getFlower().getId();
        this.quantity = orderFlower.getQuantity();
    }
    public long getOrderId(){
        return orderId;
    }
    public long getFlowerId(){ return flowerId; }
    public int getQuantity(){
        return quantity;
    }
}
