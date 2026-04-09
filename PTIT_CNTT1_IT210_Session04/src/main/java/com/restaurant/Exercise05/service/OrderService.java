package com.restaurant.Exercise05.service;

import com.restaurant.Exercise05.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceExercise05")
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String getOrder(Long id) {
        return orderRepository.getOrder(id);
    }
    public String createOrder() {
        return orderRepository.createOrder();
    }
    public String cancelOrder(Long id) {
        return orderRepository.cancelOrder(id);
    }
}
