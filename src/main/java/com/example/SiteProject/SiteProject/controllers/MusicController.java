package com.example.SiteProject.SiteProject.controllers;

import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicPageResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicResponseDTO;
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
public class MusicController {

    @Autowired
    private final MusicService musicService;

    @PostMapping(value = "/music")
    public ResponseEntity<MusicResponseDTO> save(
            @RequestBody @Valid MusicDTO music) {

        MusicResponseDTO response = musicService.save(music);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/music")
    public ResponseEntity<MusicPageResponseDTO> find(
            @RequestParam(value = "genres", required = false) List<String> genres,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        MusicPageResponseDTO response = musicService.find(genres, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/music/{id}")
    public ResponseEntity<MusicResponseDTO> update(@PathVariable Long id, @RequestBody MusicDTO music) {

        MusicResponseDTO response = musicService.update(id, music);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/music/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        musicService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
