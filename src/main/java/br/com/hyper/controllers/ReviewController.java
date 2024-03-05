package br.com.hyper.controllers;

import br.com.hyper.dtos.requests.ReviewRequestDTO;
import br.com.hyper.dtos.responses.ReviewResponseDTO;
import br.com.hyper.dtos.responses.pages.ReviewPageResponseDTO;
import br.com.hyper.services.ReviewService;
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
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;

    @PostMapping(value = "/review")
    public ResponseEntity<ReviewResponseDTO> save(
            @RequestBody @Valid ReviewRequestDTO collection) {

        ReviewResponseDTO response = reviewService.save(collection);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/review")
    public ResponseEntity<ReviewPageResponseDTO> find(
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        ReviewPageResponseDTO response = reviewService.find(customerId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/review/{id}")
    public ResponseEntity<ReviewResponseDTO> update(@PathVariable Long id, @RequestBody ReviewRequestDTO collection) {

        ReviewResponseDTO response = reviewService.update(id, collection);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/review/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        reviewService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
