package com.example.hyper.services;

import com.example.hyper.dtos.EmailDTO;

public interface EmailService {

    public EmailDTO sendEmail(EmailDTO email);
}
