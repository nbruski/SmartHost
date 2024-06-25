package com.java.smarthost.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodeEnum {

    UNKNOWN_ERROR("unknown.error"),
    NOT_FOUND("not.found");

    private final String code;

}
