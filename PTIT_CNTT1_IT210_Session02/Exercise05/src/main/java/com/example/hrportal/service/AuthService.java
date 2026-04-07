package com.example.hrportal.service;

import com.example.hrportal.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    private final List<UserAccount> accounts = new ArrayList<>();

    public AuthService() {
        accounts.add(new UserAccount("hr_manager", "hr123", "hr_manager"));
        accounts.add(new UserAccount("hr_staff", "staff456", "hr_staff"));
    }

    public UserAccount authenticate(String username, String password) {
        for (UserAccount account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }

}