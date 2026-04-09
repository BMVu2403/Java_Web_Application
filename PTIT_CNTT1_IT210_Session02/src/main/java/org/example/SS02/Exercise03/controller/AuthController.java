package org.example.SS02.Exercise03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @RequestMapping("/login")
    public String loginPage() {
        return "Bai3/login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("username") String username, // Thêm ("username")
            @RequestParam("password") String password, // Thêm ("password")
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (("admin".equals(username) && "admin123".equals(password)) ||
                ("staff".equals(username) && "staff123".equals(password))) {

            session.setAttribute("loggedUser", username);
            session.setAttribute("role", "admin".equals(username) ? "Quản lý" : "Nhân viên");
            return "redirect:/orders";
        } else {
            redirectAttributes.addFlashAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            return "redirect:/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Hủy session
        return "redirect:/login";
    }
}