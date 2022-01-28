package com.example.demo.controllers.view;

import com.example.demo.card.Card;
import com.example.demo.card.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("client/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/activation")
    public String getActivationPage(@RequestParam("cardNumber") String cardNumber, Model model){
        Card cardToActivation = cardService.getCard(cardNumber);
        model.addAttribute("card", cardToActivation);
        return "/client/cardActivation";
    }

    @GetMapping("/updateCard")
    public String getSetNewPinPage(@RequestParam("cardNumber") String cardNumber, Model model){
        Card cardToActivation = cardService.getCard(cardNumber);
        model.addAttribute("card", cardToActivation);
        return "/client/cardSetNewPIN";
    }
}
