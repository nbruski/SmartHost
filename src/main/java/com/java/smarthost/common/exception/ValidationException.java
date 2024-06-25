package com.java.smarthost.common.exception;

import com.java.smarthost.common.validation.error.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
public class ValidationException extends BaseRuntimeException {

    private List<ValidationError> validationErrors;

}
