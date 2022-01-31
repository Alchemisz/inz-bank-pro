package com.example.demo.user;

import com.example.demo.security.priviledges.UserPriviledges;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertAndGetUserTest() {
        User user = new User("user1", "user1", UserPriviledges.getClientPriviledges());
        userRepository.insertUser(user);
        assertEquals(user.getLogin(), userRepository.getUser("user1").getLogin());
    }

    @Test
    public void removeUserTest() {
        User user = new User("user2", "user2", UserPriviledges.getClientPriviledges());
        userRepository.insertUser(user);
        userRepository.deleteUser("user2");
        assertNotEquals("user2", userRepository.getUser("user2").getLogin());
    }
}
