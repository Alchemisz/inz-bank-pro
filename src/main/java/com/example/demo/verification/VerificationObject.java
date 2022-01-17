package com.example.demo.verification;

import com.example.demo.request.RequestOrder;
import lombok.Getter;

@Getter
public
class VerificationObject {
    String code;
    RequestOrder requestOrder;

    public VerificationObject(String code, RequestOrder requestOrder) {
        this.code = code;
        this.requestOrder = requestOrder;
    }
}
