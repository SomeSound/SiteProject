package br.com.hyper.controllers;

import br.com.hyper.services.AlbumService;
import br.com.hyper.dtos.responses.AlbumResponseDTO;
import br.com.hyper.dtos.requests.AlbumRequestDTO;
import br.com.hyper.dtos.responses.pages.AlbumPageReponseDTO;
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
public class AlbumController {

    @Autowired
    private final AlbumService albumService;

    @PostMapping(value = "/album")
    public ResponseEntity<AlbumResponseDTO> save(@RequestBody @Valid AlbumRequestDTO album) {

        AlbumResponseDTO response = albumService.save(album);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/album")
    public ResponseEntity<AlbumPageReponseDTO> find(
            @RequestParam(value = "names", required = false) List<String> names,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        AlbumPageReponseDTO response = albumService.find(names, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/album/{id}")
    public ResponseEntity<AlbumResponseDTO> update(@PathVariable Long id, @RequestBody AlbumRequestDTO artist) {

        AlbumResponseDTO response = albumService.update(id, artist);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/album/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        albumService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}