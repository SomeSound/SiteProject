package com.example.hyper.controllers;

import com.example.hyper.dtos.ArtistDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ArtistController {

    @PostMapping("/artist")
    public ResponseEntity<ArtistDTO> crateArtist(@RequestBody ArtistDTO artist) {

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    }



