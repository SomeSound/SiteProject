package com.example.SiteProject.SiteProject.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/artist")
@RequiredArgsConstructor
public class ArtistController {

    @GetMapping
    public String getString(){
        return "OK";
    }


}
