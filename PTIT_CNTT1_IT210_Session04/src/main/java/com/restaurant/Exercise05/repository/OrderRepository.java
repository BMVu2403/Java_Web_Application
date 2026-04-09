package com.restaurant.Exercise05.repository;

import org.springframework.stereotype.Repository;

@Repository("RepositoryExercise05")
public class OrderRepository {
    public String getOrder(Long id) {
        return "Chi tiet don hang so: " + id;
    }

    public String createOrder() {
        return "Tao moi don hang thanh cong";
    }

    public String cancelOrder(Long id) {
        return "Da huy don hang so: " + id;
    }

    public String getAllOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
    }
}
