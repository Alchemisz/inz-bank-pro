package com.example.demo.security;


import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthServiceTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockHttpSession mockhttp;



    @Test
    public void validRequestAuthTest()
    {

        User user = userRepository.getUser("test");

        String login = user.getLogin();
        String pass = user.getPass();

        String auth = authService.requestAuth(login, pass);

        assertNotNull(auth);
    }


    @Test
    public void inValidRequestAuthTest()
    {
        String login = "test123";
        String pass = "password";

        String auth = authService.requestAuth(login, pass);

        assertNull(auth);
    }


}
