package com.example.demo.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String login) {
        return userRepository.getUser(login);
    }

    public void registerUser(User user)
    {
        userRepository.insertUser(user);
    }
    public void blockUser(User user)
    {
        user.setBlocked(!user.isBlocked());
    }
    public void deactivateUser(User user)
    {
        userRepository.deleteUser(user.getLogin());
    }

    public List<User> getUsers() {
        return userRepository.getAll();
    }

    public List<User> findMatches(String pattern) {
        return userRepository.getAll().stream().filter(e -> e.getLogin().contains(pattern)).collect(Collectors.toList());
    }
}
