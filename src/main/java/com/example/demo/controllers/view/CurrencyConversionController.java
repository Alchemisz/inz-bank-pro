package com.example.demo.controllers.view;

import com.example.demo.entities.StringWrapper;
import com.example.demo.entities.bamkAccount.BankAccount;
import com.example.demo.entities.bamkAccount.BankAccountRepository;
import com.example.demo.entities.currency.Currency;
import com.example.demo.entities.currency.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/client")
public class CurrencyConversionController {

    private CurrencyService currencyService;
    private BankAccountRepository bankAccountRepository;

    public CurrencyConversionController(CurrencyService currencyService, BankAccountRepository bankAccountRepository) {
        this.currencyService = currencyService;
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/currency/currency-conversion")
    public String currencyConversion(Model model, @RequestParam("accountNumber") String accountNumber){
        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);

        Currency bankAccountCurrency = currencyService.findById(bankAccount.getCurrency());

        model.addAttribute("currentCurrency", new StringWrapper(bankAccount.getCurrency()));
        model.addAttribute("accountNumber", accountNumber);
        model.addAttribute("currencies", bankAccountCurrency.getExchangeRates().keySet().stream().collect(Collectors.toList()));

        return "/client/currency-conversion";
    }

    @PutMapping("/currency/processing")
    public String processingCurrencyConversion(@ModelAttribute("currentCurrency") StringWrapper newCurrencyCode,
                                               @RequestParam("accountNumber") String accountNumber){

        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);
        Double currencyRate = currencyService.findById(bankAccount.getCurrency()).getExchangeRates().get(newCurrencyCode.getValue());
        currencyService.convertAccountToNewCurrency(bankAccount, newCurrencyCode.getValue(), currencyRate);

        //TODO tu będzie kiedyś update konta bankowego w bazie

        return "redirect:/client/home";
    }

}
