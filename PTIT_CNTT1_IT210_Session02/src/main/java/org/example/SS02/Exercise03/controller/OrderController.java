package org.example.SS02.Exercise03.controller;

import org.springframework.beans.factory.annotation.Autowired; // Thêm dòng này
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
public class OrderController {

    @Autowired
    private ServletContext application; // Dùng Autowired để lấy Application Scope

    @RequestMapping("/orders")
    public String listOrders(HttpSession session, Model model) { // Bỏ ServletContext ở đây
        // 1. Kiểm tra đăng nhập
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }


        List<Map<String, Object>> orders = new ArrayList<>();
        orders.add(createOrder("ORD001", "iPhone 15", 25000000.0, new Date()));
        orders.add(createOrder("ORD002", "MacBook M3", 45000000.0, new Date()));
        model.addAttribute("orders", orders);

        // 3. Cập nhật bộ đếm an toàn
        synchronized (application) {
            Integer count = (Integer) application.getAttribute("totalViewCount");
            if (count == null) count = 0;
            application.setAttribute("totalViewCount", count + 1);
        }

        return "Bai3/orders";
    }

    private Map<String, Object> createOrder(String id, String name, double price, Date date) {
        Map<String, Object> order = new HashMap<>();
        order.put("id", id);
        order.put("name", name);
        order.put("price", price);
        order.put("date", date);
        return order;
    }
}