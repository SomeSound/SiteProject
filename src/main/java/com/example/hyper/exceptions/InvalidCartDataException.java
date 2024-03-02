package com.example.hyper.exceptions;

import com.example.hyper.constants.ErrorCodes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class InvalidCartDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ErrorCodes errorCode;
    private final String details;

    public InvalidCartDataException(ErrorCodes errorCode, Exception e) {
        super(e);
        this.errorCode = errorCode;
        this.details = errorCode.getMessage();
    }

    public InvalidCartDataException(ErrorCodes errorCode, String details) {
        super(details);
        this.errorCode = errorCode;
        this.details = details;
    }

}