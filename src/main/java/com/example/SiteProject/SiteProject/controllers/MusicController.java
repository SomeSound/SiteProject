package com.example.SiteProject.SiteProject.controllers;

import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicResponseDTO;
import com.example.SiteProject.SiteProject.entities.MusicEntity;
import com.example.SiteProject.SiteProject.services.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MusicController {

    @Autowired
    private final MusicService musicService;

    @PostMapping(value = "/music")
    public ResponseEntity<MusicResponseDTO> save(
            @RequestBody @Valid MusicDTO music) {

        System.out.println("CONTROLLER - OK");
        System.out.println(music);

        MusicResponseDTO response = musicService.save(music);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
