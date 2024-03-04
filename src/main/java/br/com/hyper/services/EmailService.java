package br.com.hyper.services;


import br.com.hyper.dtos.EmailDTO;

public interface EmailService {

    EmailDTO sendEmail(EmailDTO email);
}
