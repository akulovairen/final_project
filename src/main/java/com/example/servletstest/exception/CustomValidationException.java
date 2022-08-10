package com.example.servletstest.exception;

import java.util.Map;

/**
 * Exception is thrown if validation fails
 */
public class CustomValidationException extends RuntimeException {
    private Map<String, String> errorsMap;

    public CustomValidationException(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }

    public void setErrorsMap(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }
}
