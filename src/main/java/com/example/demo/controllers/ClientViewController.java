package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientViewController {
    private String prefix = "/client/";

    @GetMapping("home")
    public String home() {
        return prefix + "accounts";
    }
}
