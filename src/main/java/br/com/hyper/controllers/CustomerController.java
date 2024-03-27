package br.com.hyper.controllers;

import br.com.hyper.dtos.requests.AuthenticationDTO;
import br.com.hyper.dtos.responses.pages.CustomerPageResponseDTO;
import br.com.hyper.dtos.requests.CustomerRequestDTO;
import br.com.hyper.dtos.responses.CustomerResponseDTO;
import br.com.hyper.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final CustomerService customerService;

    @PostMapping(value = "/customer/register")
    public ResponseEntity<CustomerResponseDTO> save(@RequestBody @Valid CustomerRequestDTO customer){

        CustomerResponseDTO response = customerService.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/customer/login")
    public ResponseEntity<Void> login(@RequestBody @Valid AuthenticationDTO authentication) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(authentication.getEmail(), authentication.getPassword());
        Authentication auth = authenticationManager.authenticate(login);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/customer/{customerId}")
    public ResponseEntity<CustomerResponseDTO> findByCustomerId(@PathVariable String customerId) {

        CustomerResponseDTO response = customerService.findByCustomerId(customerId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/customer")
    public ResponseEntity<CustomerPageResponseDTO> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        CustomerPageResponseDTO response = customerService.findAll(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/customer/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id, @RequestBody CustomerRequestDTO user) {

        CustomerResponseDTO response = customerService.update(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        customerService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
