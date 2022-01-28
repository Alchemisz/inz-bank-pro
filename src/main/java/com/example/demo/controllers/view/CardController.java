package com.example.demo.controllers.view;

import com.example.demo.bankAccount.BankEntityStatus;
import com.example.demo.card.Card;
import com.example.demo.card.CardService;
import com.example.demo.currency.StringWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("client/card")
@SessionAttributes("accountNumberWrapper")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/activation")
    public String getActivationPage(@RequestParam("cardNumber") String cardNumber, Model model,
                                    @ModelAttribute("accountNumberWrapper") StringWrapper accountNumberSession){
        Card cardToActivation = cardService.getCard(cardNumber);

        if(cardToActivation.getStatus().getTypeName().equals(BankEntityStatus.BLOCKED.getTypeName()))
            return "redirect:/client/account?accountNumber="+accountNumberSession.getValue();

        model.addAttribute("accountNumberSession", accountNumberSession);
        model.addAttribute("card", cardToActivation);
        return "/client/cardActivation";
    }

    @GetMapping("/updateCard")
    public String getSetNewPinPage(@RequestParam("cardNumber") String cardNumber, Model model,
                                   @ModelAttribute("accountNumberWrapper") StringWrapper accountNumberSession){
        model.addAttribute("accountNumberSession", accountNumberSession);
        Card cardToActivation = cardService.getCard(cardNumber);

        String cardStatus = cardToActivation.getStatus().getTypeName();
        if(cardStatus.equals(BankEntityStatus.BLOCKED.getTypeName()) ||
            cardStatus.equals(BankEntityStatus.INACTIVE.getTypeName()))
            return "redirect:/client/account?accountNumber="+accountNumberSession.getValue();

        model.addAttribute("card", cardToActivation);
        return "/client/cardSetNewPIN";
    }
}
