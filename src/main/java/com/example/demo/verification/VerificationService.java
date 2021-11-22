package com.example.demo.verification;

import com.example.demo.entities.security.LoginRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class VerificationService {
    private VerificationRepository verificationRepository;

    private Long code;

    private String getCode() {
        String code = this.code.toString();
        this.code += 1;
        return code;
    }

    public VerificationService(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
        this.code = 0L;
    }

    public Map<String, String> verifyCode(String requestId, String code) {
        Map<String, String> response = new HashMap<>();
        VerificationObject request = verificationRepository.getVerificationObject(requestId);
        if(request != null) {
            if(request.getCode().equals(code)) {
                request.getRequestOrder().execute();
                verificationRepository.removeVerificationObject(requestId);
                response.put("verified", "true");
            } else {
                response.put("errorMsg", "incorrect code");
            }
        } else {
            response.put("errorMsg", "request not found");
        }
        return response;
    }

    public String registerLoginRequest(LoginRequest loginRequest) {
        VerificationObject verificationObject = new VerificationObject(getCode(), loginRequest);
        String id = verificationRepository.registerVerificationObject(verificationObject);
        System.out.println("registered login request: id = " + id);
        return id;
    }
}
