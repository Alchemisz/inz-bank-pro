package com.example.demo.verification.verificator;

import com.example.demo.request.LoginRequest;
import com.example.demo.security.priviledges.UserPriviledges;
import com.example.demo.user.User;
import com.example.demo.verification.VerificationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginVerificatorTest {
    @Autowired
    private VerificationService verificationService;

    private volatile List<String> logger = new ArrayList<>();

    private VerificationStrategyFactory factoryMock;
    private VerificatorAbstractFactory verificatorAbstractFactory;

    @Before
    public void init() {
        this.factoryMock = new VerificationStrategyFactory() {

            @Override
            public VerificationStrategy getDesiredStrategy(VerificationType type) {
                return new VerificationStrategy() {
                    @Override
                    public void verify(String message) {
                        logger.add(message);
                    }
                };
            }
        };
        this.verificatorAbstractFactory = new VerificatorAbstractFactory(factoryMock, verificationService);
    }

    @Test
    public void LoginVerificatorTest() {


        AbstractVerificator verificator = verificatorAbstractFactory.getLoginVerificator(VerificationType.EMAIL);
        assertEquals(verificator.getClass(), LoginVerificator.class);

        HttpSession http = new HttpSession() {
            @Override
            public long getCreationTime() {
                return 0;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public long getLastAccessedTime() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public void setMaxInactiveInterval(int i) {

            }

            @Override
            public int getMaxInactiveInterval() {
                return 0;
            }

            @Override
            public HttpSessionContext getSessionContext() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String[] getValueNames() {
                return new String[0];
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void putValue(String s, Object o) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public void removeValue(String s) {

            }

            @Override
            public void invalidate() {

            }

            @Override
            public boolean isNew() {
                return false;
            }
        };
        User user = new User("test", "test", UserPriviledges.getClientPriviledges());
        String id = verificator.startVerification(new LoginRequest(http, user));

        assertFalse(logger.isEmpty());
        assertTrue(logger.get(0).contains("login") && logger.get(0).contains("code"));
        logger.clear();
    }
}
