package com.example.demo.controllers.view;

import com.example.demo.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/client")
public class ClientViewController {
    private String prefix = "/client/";

    @GetMapping("home")
    public String home(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");

        model.addAttribute("userName", user.getLogin());
        model.addAttribute("userAccounts", user.getAccounts());
        return prefix + "accounts";
    }
}
