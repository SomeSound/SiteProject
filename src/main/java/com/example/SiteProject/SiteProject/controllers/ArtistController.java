package com.example.SiteProject.SiteProject.controllers;

import com.example.SiteProject.SiteProject.dtos.ArtistDTO;
import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.example.SiteProject.SiteProject.dtos.responses.ArtistPageResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.ArtistResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicResponseDTO;
import com.example.SiteProject.SiteProject.services.ArtistService;
import com.example.SiteProject.SiteProject.services.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ArtistController {

    @Autowired
    private final ArtistService artistService;

    @PostMapping(value = "/artist")
    public ResponseEntity<ArtistResponseDTO> save(
            @RequestBody @Valid ArtistDTO artist) {

        ArtistResponseDTO response = artistService.save(artist);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/artist")
    public ResponseEntity<ArtistPageResponseDTO> find(
            @RequestParam(value = "names", required = false) List<String> names,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        ArtistPageResponseDTO response = artistService.find(names, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/artist/{id}")
    public ResponseEntity<ArtistResponseDTO> update(@PathVariable Long id, @RequestBody ArtistDTO artist) {

        ArtistResponseDTO response = artistService.update(id, artist);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/artist/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        artistService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
