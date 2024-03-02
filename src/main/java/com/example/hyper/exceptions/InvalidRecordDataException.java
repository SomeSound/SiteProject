package com.example.hyper.exceptions;

import com.example.hyper.constants.ErrorCodes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class InvalidRecordDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ErrorCodes errorCode;
    private final String details;

    public InvalidRecordDataException(ErrorCodes errorCode, Exception e) {
        super(e);
        this.errorCode = errorCode;
        this.details = errorCode.getMessage();
    }

    public InvalidRecordDataException(ErrorCodes errorCode, String details) {
        super(details);
        this.errorCode = errorCode;
        this.details = details;
    }

}