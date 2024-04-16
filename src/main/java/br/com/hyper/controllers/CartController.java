package br.com.hyper.controllers;

import br.com.hyper.services.CartService;
import br.com.hyper.dtos.requests.CartRequestDTO;
import br.com.hyper.dtos.responses.CartResponseDTO;
import br.com.hyper.dtos.responses.pages.CartPageResponseDTO;
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
public class CartController {

    @Autowired
    private final CartService cartService;

    @PostMapping(value = "/cart")
    public ResponseEntity<CartResponseDTO> save(@RequestBody @Valid CartRequestDTO cart) {

        CartResponseDTO response = cartService.save(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/cart")
    public ResponseEntity<CartPageResponseDTO> find(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        CartPageResponseDTO response = cartService.find(pageable);

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
