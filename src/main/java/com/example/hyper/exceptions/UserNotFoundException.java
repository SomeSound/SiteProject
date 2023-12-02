package com.example.hyper.exceptions;

import com.example.hyper.constants.ErrorCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Getter
@EqualsAndHashCode(callSuper = true)
public class UserNotFoundException extends RuntimeException{

    private final ErrorCodes errorCodes;
    private final String details;

    public UserNotFoundException(ErrorCodes errorCode, Exception e){
        super(e);
        this.errorCodes = errorCode;
        this.details = errorCode.getMessage();
    }

    public UserNotFoundException(ErrorCodes errorCode, String details){
            super(details);
        this.errorCodes = errorCode;
        this.details = details;
    }
}
