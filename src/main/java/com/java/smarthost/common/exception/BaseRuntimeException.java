package com.java.smarthost.common.exception;

import lombok.Getter;

@Getter
public class BaseRuntimeException extends RuntimeException {
    public BaseRuntimeException() {
    }

    public BaseRuntimeException(String message) {
        super(message);
    }
}
