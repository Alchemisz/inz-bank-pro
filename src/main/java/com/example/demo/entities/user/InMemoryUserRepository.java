package com.example.demo.entities.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.demo.entities.bankAccount.BankAccountRepository;
import com.example.demo.security.priviledges.UserPriviledges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users;

    BankAccountRepository bankAccountRepository;

    @Autowired
    public InMemoryUserRepository(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.users = new ConcurrentHashMap<>();
        User user = new User("test", BCrypt.withDefaults().hashToString(5, "test".toCharArray()), UserPriviledges.getClientPriviledges());
        user.addAccount(bankAccountRepository.getBankAccount("200040003000"));
        this.users.put(user.getLogin(), user);

        User user2 = new User("test2", BCrypt.withDefaults().hashToString(5, "test2".toCharArray()), UserPriviledges.getNoPriviledges());
        user2.addAccount(bankAccountRepository.getBankAccount("345623450984"));
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
}
