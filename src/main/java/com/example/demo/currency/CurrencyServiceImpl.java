package com.example.demo.currency;


import com.example.demo.bankAccount.BankAccount;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency findById(String id) {
        return currencyRepository.findById(id);
    }

    @Override
    public List<String> findCurrenciesCodes(String id) {
        return this.findById(id).getExchangeRates().keySet().stream().collect(Collectors.toList());
    }

    @Override
    public void convertAccountToNewCurrency(BankAccount bankAccount, String newCurrencyCode) {

        Double newCurrencyRate = this.findById(bankAccount.getCurrency()).getExchangeRates().get(newCurrencyCode);
        BigDecimal rate = BigDecimal.valueOf(newCurrencyRate);
        BigDecimal newBalance = bankAccount.getBalance().multiply(rate);

////        Zaokrąglenie do 8 miejsc po przecinku (w celu zachowania dokładności konwersji)
////        Jak wyświetlam w thymeleaf to wtedy zaokrąglam do 2
//        BigDecimal rounded = newBalance.setScale(8, RoundingMode.HALF_UP);

        bankAccount.setCurrency(newCurrencyCode);
        bankAccount.setBalance(newBalance);
    }

    @Override
    public List<String> getCurrenciesCodes() {
        return currencyRepository.getCurrenciesCodes();
    }

    @Override
    public List<String> getCurrenciesWithoutCode(String code) {
        return currencyRepository.getCurrenciesCodes()
                .stream()
                .filter(e -> !e.equals(code))
                .collect(Collectors.toList());
    }
}
