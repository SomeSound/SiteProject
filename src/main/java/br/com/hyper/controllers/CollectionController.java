package br.com.hyper.controllers;

import br.com.hyper.dtos.requests.CollectionRequestDTO;
import br.com.hyper.dtos.responses.CollectionResponseDTO;
import br.com.hyper.dtos.responses.pages.CollectionPageResponseDTO;
import br.com.hyper.services.CollectionService;
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
public class CollectionController {

    @Autowired
    private final CollectionService collectionService;

    @PostMapping(value = "/collection")
    public ResponseEntity<CollectionResponseDTO> save(
            @RequestBody @Valid CollectionRequestDTO collection) {

        CollectionResponseDTO response = collectionService.save(collection);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/collection")
    public ResponseEntity<CollectionPageResponseDTO> find(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        CollectionPageResponseDTO response = collectionService.find(name, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/collection/{id}")
    public ResponseEntity<CollectionResponseDTO> update(@PathVariable Long id, @RequestBody CollectionRequestDTO collection) {

        CollectionResponseDTO response = collectionService.update(id, collection);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/collection/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        collectionService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
