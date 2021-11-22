package com.example.demo.controllers.rest;

import com.example.demo.verification.VerificationService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/verification/")
public class VerificationController {

    VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PutMapping("/verify/{id}")
    public Map<String, String> verify(@PathVariable String id, @RequestBody JsonNode payload, HttpServletRequest httpServletRequest) {
        JsonNode codeNode = payload.get("code");
        if(codeNode != null) {
            return verificationService.verifyCode(id, codeNode.toString());
        }
        Map<String, String> response = new HashMap<>();
        response.put("errorMsg", "no code field");
        return response;
    }
}
