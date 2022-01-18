package com.example.demo.user;

import com.example.demo.security.priviledges.UserPriviledges;
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

    public void registerAndGetUserTest() {
        User user = new User("test", "test", UserPriviledges.getClientPriviledges());
        userService.registerUser(user);
        assertEquals(user, userService.getUser("test"));
    }

    public void blockUserTest() {
        User user = new User("test", "test", UserPriviledges.getClientPriviledges());
        userService.registerUser(user);
        userService.blockUser(user);
        assertTrue(user.isBlocked());
        userService.blockUser(user);
        assertFalse(user.isBlocked());
    }
}
