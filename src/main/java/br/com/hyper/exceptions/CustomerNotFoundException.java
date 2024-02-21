package br.com.hyper.exceptions;

import br.com.hyper.constants.ErrorCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Getter
@EqualsAndHashCode(callSuper = true)
public class CustomerNotFoundException extends RuntimeException{

    private final ErrorCodes errorCodes;
    private final String details;

    public CustomerNotFoundException(ErrorCodes errorCode, Exception e){
        super(e);
        this.errorCodes = errorCode;
        this.details = errorCode.getMessage();
    }

    public CustomerNotFoundException(ErrorCodes errorCode, String details){
            super(details);
        this.errorCodes = errorCode;
        this.details = details;
    }
}
