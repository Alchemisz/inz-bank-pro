package com.example.demo.controllers.rest;

import com.example.demo.card.Card;
import com.example.demo.card.CardService;
import com.example.demo.card.builder.directorFactory.CardDirectorFactory;
import com.example.demo.request.RequestFactory;
import com.example.demo.request.RequestOrder;
import com.example.demo.verification.verificator.AbstractVerificator;
import com.example.demo.verification.verificator.VerificationType;
import com.example.demo.verification.verificator.VerificatorAbstractFactory;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/card")
public class CardRestController {

    private final CardService cardService;
    private final RequestFactory requestFactory;
    private final VerificatorAbstractFactory verificatorAbstractFactory;
    private final CardDirectorFactory cardDirectorFactory;

    public CardRestController(CardService cardService, RequestFactory requestFactory, VerificatorAbstractFactory verificatorAbstractFactory, CardDirectorFactory cardDirectorFactory) {
        this.cardService = cardService;
        this.requestFactory = requestFactory;
        this.verificatorAbstractFactory = verificatorAbstractFactory;
        this.cardDirectorFactory = cardDirectorFactory;
    }

    @PostMapping("/create")
    public Map<String, String> createCard(@RequestBody JsonNode payload){
        String accountNumber = payload.get("accountNumber").toString().replace("\"", "");
        RequestOrder createCardRequest = requestFactory.createCreateCardRequest(cardService, accountNumber, cardDirectorFactory.getCardDirector());
        AbstractVerificator createCardVerificator = verificatorAbstractFactory.getCreateCardVerificator(VerificationType.EMAIL);
        String id = createCardVerificator.startVerification(createCardRequest);
        Map<String, String> response = new HashMap<>();
        response.put("requestId", id);
        return response;
    }

    @PutMapping("/activate")
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

    @PutMapping("/setNewPin")
    public Map<String, String> setNewPIN(@RequestBody JsonNode payload){
        String cardNumber = payload.get("cardNumber").toString().replace("\"", "");
        Integer passedPIN = payload.get("passedPIN").asInt();
        Integer newPIN = payload.get("newPIN").asInt();
        Map<String, String> response = new HashMap<>();
        response.put("response", (cardService.trySetNewPIN(cardNumber, passedPIN, newPIN) ? HttpStatus.OK.toString() : HttpStatus.BAD_REQUEST.toString()));
        return response;
    }

    @PutMapping("/block")
    public Map<String, String> block(@RequestBody JsonNode payload){
        String cardNumber = payload.get("cardNumber").toString().replace("\"", "");
        RequestOrder blockCardRequest = requestFactory.createBlockCardRequest(cardService, cardNumber);
        AbstractVerificator blockCardVerificator = verificatorAbstractFactory.getBlockCardVerificator(VerificationType.EMAIL);
        String id = blockCardVerificator.startVerification(blockCardRequest);
        Map<String, String> response = new HashMap<>();
        response.put("requestId", id);
        return response;
    }

}
