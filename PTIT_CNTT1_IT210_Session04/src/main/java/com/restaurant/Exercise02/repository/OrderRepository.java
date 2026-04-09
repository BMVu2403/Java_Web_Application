package com.restaurant.Exercise02.repository;

import org.springframework.stereotype.Repository;

@Repository("RepositoryExercise02")
public class OrderRepository {
    public String getAllOrders() {
        return "Danh sach toan bo don hang";
    }

    public String getOrderById(Long id) {
        return "Thong tin don hang voi ID: " + id;
    }
}
