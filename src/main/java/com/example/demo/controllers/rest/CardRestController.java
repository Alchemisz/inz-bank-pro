package com.example.demo.controllers.rest;

import com.example.demo.card.Card;
import com.example.demo.card.CardService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class CardRestController {

    private final CardService cardService;

    public CardRestController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/card")
    public Map<String, String> cardActivationVerification(@RequestBody JsonNode payload){
        String cardNumber = payload.get("cardNumber").toString().replace("\"", "");
        Integer pin = payload.get("pin").asInt();
        Card card = cardService.getCard(cardNumber);
        System.out.println(card);
        return null;
    }

}
