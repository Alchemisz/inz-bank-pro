package com.example.demo.controllers.rest;

import com.example.demo.analitics.AnaliticsService;
import com.example.demo.entities.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnaliticsController {
    private AnaliticsService analiticsService;

    public AnaliticsController(AnaliticsService analiticsService) {
        this.analiticsService = analiticsService;
    }

    @GetMapping("user/{id}")
    public Map<String, Object> getSummaryUserRaport(@PathVariable String id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user.getUserPriviledges().isHasEmployeeRights()) {
            return analiticsService.generateUserSummaryReport(id);
        }
        return new HashMap<>();
    }
}
