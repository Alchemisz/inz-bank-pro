package com.example.demo.user;

import com.example.demo.security.priviledges.UserPriviledges;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertAndGetUserTest() {
        User user = new User("test", "test", UserPriviledges.getClientPriviledges());
        userRepository.insertUser(user);
        assertEquals(user, userRepository.getUser("test"));
    }

    @Test
    public void removeUserTest() {
        User user = new User("test2", "test2", UserPriviledges.getClientPriviledges());
        userRepository.insertUser(user);
        userRepository.deleteUser("test2");
        assertNull(userRepository.getUser("test2"));
    }
}
