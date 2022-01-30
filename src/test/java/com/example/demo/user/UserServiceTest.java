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
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void registerAndGetUserTest() {
        User user = new User("user", "user", UserPriviledges.getClientPriviledges());
        userService.registerUser(user);
        assertEquals(user.getLogin(), userService.getUser("user").getLogin());
    }

    @Test
    public void blockUserTest() {
        User user = new User("test", "test", UserPriviledges.getClientPriviledges());
        userService.registerUser(user);
        userService.blockUser(user);
        assertTrue(user.isBlocked());
        userService.blockUser(user);
        assertFalse(user.isBlocked());
    }
}
