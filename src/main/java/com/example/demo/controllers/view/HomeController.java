package com.example.demo.controllers.view;

import com.example.demo.entities.user.User;
import com.example.demo.security.priviledges.UserPriviledges;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller()
@RequestMapping("/home")
public class HomeController {
    HttpSession httpSession;

    public HomeController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String home(HttpSession httpSession) {
        return "index";
    }
}
