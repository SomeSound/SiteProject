package com.example.SiteProject.SiteProject.controllers;

import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.example.SiteProject.SiteProject.services.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class MusicController {

//    @Autowired
//    private static MusicService musicService;

    @PostMapping
    public ResponseEntity<MusicDTO> createMusic() {

//        MusicDTO response = musicService.save(music);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
