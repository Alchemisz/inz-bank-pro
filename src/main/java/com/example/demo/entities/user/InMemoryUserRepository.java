package com.example.demo.entities.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.demo.security.priviledges.UserPriviledges;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users;

    public InMemoryUserRepository() {
        this.users = new HashMap<>();
        User user = new User("test", BCrypt.withDefaults().hashToString(5, "test".toCharArray()), UserPriviledges.getClientPriviledges());
        this.users.put(user.getLogin(), user);

        User user2 = new User("test2", BCrypt.withDefaults().hashToString(5, "test2".toCharArray()), UserPriviledges.getNoPriviledges());
        this.users.put(user2.getLogin(), user2);
    }

    @Override
    public Optional<User> getUser(String login) {
        return Optional.ofNullable(users.get(login));
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
