package br.com.hyper.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class EmailDTO {

    @NotEmpty(message = "Invalid name, can not be empty")
    private String owner;

    @Email
    @NotEmpty(message = "Invalid email, can not be empty")
    private String emailFrom;

    @NotEmpty(message = "Invalid subject, can not be empty")
    private String subject;

    @Email
    @NotEmpty(message = "Invalid email, can not be empty")
    private String emailTo;

    @NotEmpty(message = "Invalid text, can not be empty")
    private String text;

}
