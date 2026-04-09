package com.restaurant.Exercise05.controller;

import com.restaurant.Exercise05.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Controller("orderControllerExercise05")
@RequestMapping("/Exercise05")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getOrder(@PathVariable("id") Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    @ResponseBody
    public String createOrder() {
        return orderService.createOrder();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String cancelOrder(@PathVariable("id") Long id) {
        return orderService.cancelOrder(id);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public String handleTypeMismatch() {
        return "ID don hang phai la so hop le";
    }
}
