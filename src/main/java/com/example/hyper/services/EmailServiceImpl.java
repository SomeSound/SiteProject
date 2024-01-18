package com.example.hyper.services;

import com.example.hyper.dtos.EmailDTO;
import com.example.hyper.entities.EmailEntity;
import com.example.hyper.enums.StatusEmail;
import com.example.hyper.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Autowired
    private final EmailRepository emailRepository;

//    @Autowired
//    private JavaMailSender javaMailSender;

    @Autowired
    private ModelMapper modelMapper;


    public EmailDTO sendEmail(EmailDTO email) {

        EmailEntity emailEntity = modelMapper.map(email, EmailEntity.class);
        emailEntity.setSendDate(ZonedDateTime.now());

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailEntity.getEmailFrom());
            message.setTo(emailEntity.getEmailTo());
            message.setSubject(emailEntity.getSubject());
            message.setText(emailEntity.getText());
//            javaMailSender.send(message);

            emailEntity.setStatus(StatusEmail.SENT);
        }catch (MailException e){
            emailEntity.setStatus(StatusEmail.ERROR);
        }
        emailRepository.save(emailEntity);
        return modelMapper.map(emailEntity, EmailDTO.class);
    }

}
