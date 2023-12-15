package com.example.hyper.controllers;

import com.example.hyper.dtos.requests.CustomerRequestDTO;
import com.example.hyper.dtos.responses.pages.CustomerPageResponseDTO;
import com.example.hyper.dtos.responses.CustomerResponseDTO;
import com.example.hyper.services.CustomerService;
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
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @PostMapping(value = "/user")
    public ResponseEntity<CustomerResponseDTO> save(
            @RequestBody @Valid CustomerRequestDTO user){

        CustomerResponseDTO response = customerService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping(value = "/user")
    public ResponseEntity<CustomerPageResponseDTO> find(
            @RequestParam(value = "names", required = false) List<String> names,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        CustomerPageResponseDTO response = customerService.find(names, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id, @RequestBody CustomerRequestDTO user) {

        CustomerResponseDTO response = customerService.update(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        customerService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
