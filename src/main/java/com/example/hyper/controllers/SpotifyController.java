package com.example.hyper.controllers;

import com.example.hyper.services.spotify.SpotifyProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.net.URISyntaxException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SpotifyController {

    @Autowired
    private final SpotifyProxy spotifyProxy;

    @GetMapping(value = "/spotify")
    public ResponseEntity<Paging<Track>> find(@RequestParam(value = "title") String id) {

        Paging<Track> response = spotifyProxy.searchTrack(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
