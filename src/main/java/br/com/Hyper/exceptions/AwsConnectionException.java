package br.com.hyper.exceptions;

import br.com.hyper.constants.ErrorCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Getter
public class AwsConnectionException {
    private static final long serialVersionUID = 1L;

    private final ErrorCodes errorCode;
    private final String details;

    public AwsConnectionException(ErrorCodes errorCode, String details) {
        this.errorCode = errorCode;
        this.details = details;
    }
}
