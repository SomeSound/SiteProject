package br.com.hyper.exceptions;

import br.com.hyper.constants.ErrorCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Getter
@EqualsAndHashCode(callSuper = true)
public class ArtistNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final ErrorCodes errorCode;
    private final String details;

    public ArtistNotFoundException(ErrorCodes errorCode, Exception e) {
        super(e);
        this.errorCode = errorCode;
        this.details = errorCode.getMessage();
    }

    public ArtistNotFoundException(ErrorCodes errorCode, String details) {
        super(details);
        this.errorCode = errorCode;
        this.details = details;
    }

}
