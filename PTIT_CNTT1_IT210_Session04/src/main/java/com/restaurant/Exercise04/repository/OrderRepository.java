package com.restaurant.Exercise04.repository;

import org.springframework.stereotype.Repository;

@Repository("RepositoryBai4")
public class OrderRepository {
    public String getAllOrders() {
        return "Danh sach toan bo don hang";
    }

    public String getOrderById(Long id) {
        return "Thong tin don hang voi ID: " + id;
    }
}
