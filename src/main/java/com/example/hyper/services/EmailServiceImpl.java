package com.example.hyper.services;

import com.example.hyper.dtos.EmailDTO;
import com.example.hyper.dtos.responses.MusicResponseDTO;
import com.example.hyper.entities.EmailEntity;
import com.example.hyper.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private EmailRepository emailRepository;

    private final ModelMapper modelMapper;

    @Override
    @Autowired
    public EmailDTO save(EmailDTO email) {

        EmailEntity emailEntity;

        try{
            emailEntity = modelMapper.map(email, EmailEntity.class);

            emailEntity = emailRepository.save(emailEntity);

            return modelMapper.map(emailEntity, EmailDTO.class);

        }catch (DataIntegrityViolationException e){
            return null; // Implementar exception
        }
    }
}
