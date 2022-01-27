package com.example.demo.controllers.rest;

import com.example.demo.card.Card;
import com.example.demo.card.CardService;
import com.example.demo.request.RequestFactory;
import com.example.demo.request.RequestOrder;
import com.example.demo.verification.verificator.AbstractVerificator;
import com.example.demo.verification.verificator.VerificationType;
import com.example.demo.verification.verificator.VerificatorAbstractFactory;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class CardRestController {

    private final CardService cardService;
    private final RequestFactory requestFactory;
    private final VerificatorAbstractFactory verificatorAbstractFactory;

    public CardRestController(CardService cardService, RequestFactory requestFactory, VerificatorAbstractFactory verificatorAbstractFactory) {
        this.cardService = cardService;
        this.requestFactory = requestFactory;
        this.verificatorAbstractFactory = verificatorAbstractFactory;
    }

    @PostMapping("/card")
    public Map<String, String> cardActivationVerification(@RequestBody JsonNode payload){
        String cardNumber = payload.get("cardNumber").toString().replace("\"", "");
        Integer pin = payload.get("pin").asInt();
        Card card = cardService.getCard(cardNumber);
        RequestOrder activateCardRequest = requestFactory.createActivateCardRequest(cardService, card, pin);
        AbstractVerificator activateCardVerificator = verificatorAbstractFactory.getActivateCardVerificator(VerificationType.EMAIL);
        String id = activateCardVerificator.startVerification(activateCardRequest);
        Map<String, String> response = new HashMap<>();
        response.put("requestId", id);
        return response;
    }

}
