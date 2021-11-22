package com.example.demo.verification;

import com.example.demo.entities.security.LoginRequest;
import com.example.demo.entities.user.User;
import com.example.demo.security.priviledges.UserPriviledges;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class VerificationServiceTest {
    VerificationService verificationService;

    public VerificationServiceTest() {
        this.verificationService = new VerificationService(new InMemoryVerificationRepository());
    }

    @Test
    public void badRequest() {
        User user = new User("test", "test", UserPriviledges.getClientPriviledges());
        HttpSession session = new MockHttpSession();
        LoginRequest loginRequest = new LoginRequest(session, user);
        String id = verificationService.registerLoginRequest(loginRequest);

        Map<String, String> response = verificationService.verifyCode("100", "0");
        assertTrue(response.containsKey("errorMsg"));
        assertNull(session.getAttribute("user"));
    }

    @Test
    public void badCode() {
        User user = new User("test", "test", UserPriviledges.getClientPriviledges());
        HttpSession session = new MockHttpSession();
        LoginRequest loginRequest = new LoginRequest(session, user);
        String id = verificationService.registerLoginRequest(loginRequest);

        Map<String, String> response = verificationService.verifyCode(id, "100");
        assertTrue(response.containsKey("errorMsg"));
        assertNull(session.getAttribute("user"));
    }

    @Test
    public void registerLoginRequestTest() {
        User user = new User("test", "test", UserPriviledges.getClientPriviledges());
        HttpSession session = new MockHttpSession();
        LoginRequest loginRequest = new LoginRequest(session, user);
        String id = verificationService.registerLoginRequest(loginRequest);

        Map<String, String> response = verificationService.verifyCode(id, "0");
        assertThat(session.getAttribute("user"), instanceOf(User.class));
    }
}
