package com.example.demo.controllers;

import com.example.demo.entities.user.UserInfo;
import com.example.demo.security.priviledges.UserPriviledges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/test")
public class MainController {
    UserInfo userInfo;

    public MainController(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @GetMapping("/secured")
    public String home() {
        String privs = "";
        UserPriviledges userPriviledges = userInfo.getUserPriviledges();
        if(userPriviledges.isHasClientRights()) {
            privs += "i have client rights";
        } else {
            privs += "i have no rights";
        }
        System.out.println(privs);
        return privs;
    }
}
