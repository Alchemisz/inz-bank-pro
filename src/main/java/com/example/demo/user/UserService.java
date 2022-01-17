package com.example.demo.user;

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
        System.out.println("Blocked user: "+user.getLogin());
    }
    public void deactivateUser(User user)
    {
        userRepository.deleteUser(user.getLogin());
    }

}
