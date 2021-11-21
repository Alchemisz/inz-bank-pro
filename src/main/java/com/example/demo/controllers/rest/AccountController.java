package com.example.demo.controllers.rest;

import com.example.demo.entities.bamkAccount.InMemoryBankAccountRepository;
import com.example.demo.entities.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AccountController {

    @GetMapping("/accounts")
    public List<String> getAccounst(HttpSession httpSession){
            User user = (User) httpSession.getAttribute("user");
            if (user != null){
                return user.getAccounts();
            }else {
                return null;
            }
    }

}
