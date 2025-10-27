package com.vinogradov;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserService() {
        System.out.println("UserService constructor now calles");
    }

    public String getUser() {
        return "User";
    }
}