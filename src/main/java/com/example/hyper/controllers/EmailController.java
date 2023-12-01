package com.example.hyper.controllers;

import com.example.hyper.dtos.EmailDTO;
import com.example.hyper.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @Autowired
    @PostMapping(value = "/send-mail")
    public ResponseEntity<EmailDTO> saveEmail(@RequestBody @Valid EmailDTO email ){

        EmailDTO response = emailService.save(email);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
