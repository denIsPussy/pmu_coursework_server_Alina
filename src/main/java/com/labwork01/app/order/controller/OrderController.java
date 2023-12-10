package com.labwork01.app.order.controller;

import com.labwork01.app.flower.model.FlowerDTO;
import com.labwork01.app.flower.service.FlowerService;
import com.labwork01.app.order.model.OrderDTO;
import com.labwork01.app.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {
        return new OrderDTO(orderService.findOrder(id));
    }

    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderService.findAllOrders().stream()
                .map(OrderDTO::new)
                .toList();
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
         return new OrderDTO(orderService.addOrder(orderDTO));
    }

    @PutMapping("/{id}")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
        return new OrderDTO(orderService.updateOrder(orderDTO));
    }

    @DeleteMapping("/{id}")
    public OrderDTO deleteOrder(@PathVariable Long id) {
        return new OrderDTO(orderService.deleteOrder(id));
    }
}
