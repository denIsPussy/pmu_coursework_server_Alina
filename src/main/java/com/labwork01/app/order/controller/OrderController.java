package com.labwork01.app.order.controller;

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
        return new OrderDTO(orderService.findById(id));
    }

    @GetMapping("/byUser/{id}")
    public List<OrderDTO> getByUserId(@PathVariable Long id) {
        return orderService.findByUserId(id).stream().map(OrderDTO::new).toList();
    }

    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderService.findAll().stream()
                .map(OrderDTO::new)
                .toList();
    }

    @PostMapping
    public OrderDTO insert(@RequestBody OrderDTO orderDTO) {
         return new OrderDTO(orderService.insert(orderDTO));
    }

    @PutMapping("/{id}")
    public OrderDTO update(@RequestBody OrderDTO orderDTO) {
        return new OrderDTO(orderService.updateOrder(orderDTO));
    }

    @DeleteMapping("/{id}")
    public OrderDTO delete(@PathVariable Long id) {
        return new OrderDTO(orderService.delete(id));
    }
}
