package com.example.demo.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.security.priviledges.UserPriviledges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users;

    BankAccountService bankAccountService;

    @Autowired
    public InMemoryUserRepository(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
        this.users = new ConcurrentHashMap<>();
        User user = new User("test", BCrypt.withDefaults().hashToString(5, "test".toCharArray()), UserPriviledges.getClientPriviledges());
        user.addAccount(bankAccountService.getBankAccounts().get(0));
        user.addAccount(bankAccountService.getBankAccounts().get(1));
        this.users.put(user.getLogin(), user);

        User user2 = new User("test2", BCrypt.withDefaults().hashToString(5, "test2".toCharArray()), UserPriviledges.getNoPriviledges());
        user2.addAccount(bankAccountService.getBankAccounts().get(2));
        this.users.put(user2.getLogin(), user2);
    }

    @Override
    public User getUser(String login) {
        return users.get(login);
    }

    @Override
    public void insertUser(User user) {
        users.put(user.getLogin(), user);
    }

    @Override
    public void deleteUser(String login) {
        users.remove(login);
    }

    @Override
    public List<User> getAll() {
        return users.values().stream().collect(Collectors.toList());
    }
}
