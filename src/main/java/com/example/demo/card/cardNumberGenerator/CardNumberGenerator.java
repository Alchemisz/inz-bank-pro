package com.example.demo.card.cardNumberGenerator;

import com.example.demo.bankAccount.accountNumberGenerator.Generator;
import com.example.demo.card.CardRepository;
import com.example.demo.card.CardService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CardNumberGenerator implements Generator {

    private final CardService cardService;

    public CardNumberGenerator(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public String generate() {
        StringBuilder stringBuilder;
        Random random = new Random();

        do {
            stringBuilder = new StringBuilder();
            stringBuilder.append(random.nextInt(9) + 1);
            for (int i = 0; i < 15; i++) {
                stringBuilder.append(random.nextInt(10));
            }
        }while (cardService.isCardNumberExists(stringBuilder.toString()));

        return stringBuilder.toString();
    }
}
