package br.com.hyper.exceptions;

import br.com.hyper.constants.ErrorCodes;
import lombok.Getter;

import java.io.Serial;

@Getter
public class PlaylistNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ErrorCodes errorCode;
    private final String details;

    public PlaylistNotFoundException(ErrorCodes errorCode, Exception e) {
        super(e);
        this.errorCode = errorCode;
        this.details = errorCode.getMessage();
    }

    public PlaylistNotFoundException(ErrorCodes errorCode, String details) {
        super(details);
        this.errorCode = errorCode;
        this.details = details;
    }

}
