package br.com.hyper.controllers;

import br.com.hyper.dtos.requests.CartRequestDTO;
import br.com.hyper.dtos.responses.artist.ArtistTrackResponseDTO;
import br.com.hyper.dtos.responses.pages.ArtistPageResponseDTO;
import br.com.hyper.services.ArtistService;
import br.com.hyper.dtos.requests.ArtistRequestDTO;
import br.com.hyper.dtos.responses.artist.ArtistResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ArtistController {

    @Autowired
    private final ArtistService artistService;

    @PostMapping(value = "/artist")
    public ResponseEntity<ArtistResponseDTO> save(@RequestBody @Valid ArtistRequestDTO artist) {

        ArtistResponseDTO response = artistService.save(artist);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/artist/{id}/cart")
    public ResponseEntity<ArtistResponseDTO> addCart(@PathVariable @Valid Long id, @RequestBody @Valid CartRequestDTO cart) {

        ArtistResponseDTO response = artistService.addCart(id, cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/artist")
    public ResponseEntity<ArtistPageResponseDTO> find(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        ArtistPageResponseDTO response = artistService.find(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/artist/:username")
    public ResponseEntity<ArtistTrackResponseDTO> findByUsername(@RequestParam String username) {

        ArtistTrackResponseDTO response = artistService.findByUsername(username);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/artist/{id}")
    public ResponseEntity<ArtistResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ArtistRequestDTO artist) {

//        ArtistResponseDTO response = artistService.update(id, artist);

        return ResponseEntity.status(HttpStatus.OK).body(new ArtistResponseDTO());
    }

    @DeleteMapping(value = "/artist/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        artistService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
