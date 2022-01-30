package com.example.demo.request;

import com.example.demo.card.Card;
import com.example.demo.card.CardService;
import com.example.demo.card.builder.CardDirector;
import com.example.demo.card.builder.directorFactory.CardDirectorFactory;
import com.example.demo.controllers.rest.AuthController;
import com.example.demo.transfers.Transfer;
import com.example.demo.transfers.TransferService;
import com.example.demo.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestFactoryTest {

    @Autowired
    private RequestFactory requestFactory;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CardService cardService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private CardDirectorFactory cardDirectorFactory;
    @Test
    public void getLoginRequestTest() {
        User user = new User();
        assertEquals(LoginRequest.class, requestFactory.createLoginRequest(httpSession, user).getClass());
    }

    @Test
    public void getCreateCardRequestTest() {
        assertEquals(CreateCardRequest.class, requestFactory.createCreateCardRequest(cardService, "", cardDirectorFactory.getCardDirector()).getClass());
    }

    @Test
    public void getBlockCardRequestTest() {
        assertEquals(BlockCardRequest.class, requestFactory.createBlockCardRequest(cardService, "").getClass());
    }

    @Test
    public void getActivateCardRequestTest() {
        assertEquals(ActivateCardRequest.class, requestFactory.createActivateCardRequest(cardService, cardDirectorFactory.getCardDirector().getCard(), 2020).getClass());
    }

    @Test
    public void getTransferRequestTest() {
        assertEquals(TransferRequest.class, requestFactory.createTransferRequest(new Transfer("", "", "", new BigDecimal(0)), transferService).getClass());
    }
}
