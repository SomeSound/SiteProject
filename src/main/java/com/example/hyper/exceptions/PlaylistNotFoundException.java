package com.example.hyper.exceptions;

import com.example.hyper.constants.ErrorCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Getter
public class PlaylistNotFoundException extends RuntimeException {
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
