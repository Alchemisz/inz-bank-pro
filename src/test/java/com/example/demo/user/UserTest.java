package com.example.demo.user;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.security.priviledges.UserPriviledges;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void addAccountTest() {
        User user = new User("test", "test", UserPriviledges.getClientPriviledges());
        assertTrue(user.getAccounts().isEmpty());
        user.addAccount(new BankAccount());
        assertFalse(user.getAccounts().isEmpty());
    }
}
