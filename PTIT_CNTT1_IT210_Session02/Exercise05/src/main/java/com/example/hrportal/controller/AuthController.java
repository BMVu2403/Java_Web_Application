package com.example.hrportal.controller;

import com.example.hrportal.model.UserAccount;
import com.example.hrportal.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserAccount account = authService.authenticate(username, password);

        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", account.getUsername());
            session.setAttribute("role", account.getRole());
            return "redirect:/employees";
        }

        request.setAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}