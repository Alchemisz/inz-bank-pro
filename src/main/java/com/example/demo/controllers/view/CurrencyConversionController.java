package com.example.demo.controllers.view;

import com.example.demo.entities.StringWrapper;
import com.example.demo.entities.bamkAccount.BankAccount;
import com.example.demo.entities.bamkAccount.BankAccountRepository;
import com.example.demo.entities.currency.Currency;
import com.example.demo.entities.currency.CurrencyRepository;
import com.example.demo.entities.currency.InMemoryCurrencyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class CurrencyConversionController {

    private CurrencyRepository currencyRepository;
    private BankAccountRepository bankAccountRepository;

    public CurrencyConversionController(CurrencyRepository currencyRepository, BankAccountRepository bankAccountRepository) {
        this.currencyRepository = currencyRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/currency/currency-conversion")
    public String currencyConversion(Model model, @RequestParam("accountNumber") String accountNumber){
        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);

        model.addAttribute("currentCurrency", new StringWrapper(bankAccount.getCurrency().getName()));
        model.addAttribute("accountNumber", accountNumber);
        model.addAttribute("currencies", currencyRepository.findAll());
        return "/client/currency-conversion";
    }

    @PutMapping("/currency/processing")
    public String processingCurrencyConversion(@ModelAttribute("currentCurrency") StringWrapper newCurrency,
                                               @RequestParam("accountNumber") String accountNumber){

        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);
        Currency currency = currencyRepository.findById(newCurrency.getValue());
        bankAccount.setCurrency(currency);

        //TODO tu będzie kiedyś update konta bankowego w bazie

        return "redirect:/client/home";
    }

}
