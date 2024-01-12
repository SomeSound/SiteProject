package com.example.hyper.controllers;

import com.example.hyper.dtos.requests.CartRequestDTO;
import com.example.hyper.dtos.responses.CartResponseDTO;
import com.example.hyper.dtos.responses.pages.CartPageResponseDTO;
import com.example.hyper.services.CartService;
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
public class CartController {

    @Autowired
    private final CartService cartService;

    @PostMapping(value = "/cart")
    public ResponseEntity<CartResponseDTO> save(
            @RequestBody @Valid CartRequestDTO cart) {

        CartResponseDTO response = cartService.save(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/cart")
    public ResponseEntity<CartPageResponseDTO> find(
            @RequestParam(value = "name", required = false) List<String> names,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        CartPageResponseDTO response = cartService.find(names, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/cart/{id}")
    public ResponseEntity<CartResponseDTO> update(@PathVariable Long id, @RequestBody CartRequestDTO cart) {

        CartResponseDTO response = cartService.update(id, cart);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/cart/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        cartService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
