package com.example.demo.verification;

import com.example.demo.entities.request.RequestOrder;
import lombok.Getter;

@Getter
class VerificationObject {
    final String code;
    final RequestOrder requestOrder;

    public VerificationObject(String code, RequestOrder requestOrder) {
        this.code = code;
        this.requestOrder = requestOrder;
    }
}
