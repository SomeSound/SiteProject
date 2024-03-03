package br.com.hyper.controllers;

import br.com.hyper.dtos.requests.OrderRequestDTO;
import br.com.hyper.dtos.responses.OrderResponseDTO;
import br.com.hyper.dtos.responses.pages.OrderPageResponseDTO;
import br.com.hyper.services.OrderService;
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
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @PostMapping(value = "/order")
    public ResponseEntity<OrderResponseDTO> save(
            @RequestBody @Valid OrderRequestDTO order) {

        OrderResponseDTO response = orderService.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/order")
    public ResponseEntity<OrderPageResponseDTO> find(
            @RequestParam(value = "name", required = false) List<String> names,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        OrderPageResponseDTO response = orderService.find(names, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/order/{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable Long id, @RequestBody OrderRequestDTO order) {

        OrderResponseDTO response = orderService.update(id, order);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/cart/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        orderService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

