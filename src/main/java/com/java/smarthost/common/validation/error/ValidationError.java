package com.java.smarthost.common.validation.error;

public record ValidationError(ValidationErrorsEnum error,
                              String message) {
}
