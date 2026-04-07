package com.demo.controller;

import com.demo.model.Order;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class OrderController {

    @GetMapping("/orders")
    public String showOrders(HttpSession session, Model model, HttpServletRequest request) {
        Object loggedUser = session.getAttribute("loggedUser");
        if (loggedUser == null) {
            return "redirect:/login";
        }

        List<Order> orders = new ArrayList<>();
        orders.add(new Order("DH001", "Laptop Dell", 18500000, createDate(2024, 10, 15)));
        orders.add(new Order("DH002", "Chuột Logitech", 650000, createDate(2024, 10, 18)));
        orders.add(new Order("DH003", "Bàn phím cơ", 1450000, createDate(2024, 10, 20)));
        orders.add(new Order("DH004", "Màn hình LG 27 inch", 5200000, createDate(2024, 10, 22)));

        model.addAttribute("orderList", orders);

        ServletContext application = request.getServletContext();

        synchronized (application) {
            Integer count = (Integer) application.getAttribute("totalViewCount");
            if (count == null) {
                count = 0;
            }
            count++;
            application.setAttribute("totalViewCount", count);
        }

        return "orders";
    }

    private java.util.Date createDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}