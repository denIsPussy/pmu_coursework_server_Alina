package com.labwork01.app.order.repository;

import com.labwork01.app.flower.model.Flower;
import com.labwork01.app.order.model.Order;
import com.labwork01.app.order.model.OrderFlowerDTO;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryExtension {
    void addFlowers(long orderId, List<OrderFlowerDTO> orderFlowers, boolean isEdit);
    void removeFlowers(long orderId, List<OrderFlowerDTO> orderFlowers);
    void removeAllFlowers(long orderId);
}
