package br.com.hyper.controllers;

import br.com.hyper.dtos.requests.LeadsRequestDTO;
import br.com.hyper.dtos.responses.LeadsResponseDTO;
import br.com.hyper.dtos.responses.pages.LeadsPageResponseDTO;
import br.com.hyper.services.LeadsService;
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
public class LeadsController {

    @Autowired
    private final LeadsService leadsService;

    @PostMapping(value = "/leads")
    public ResponseEntity<LeadsResponseDTO> save(
            @RequestBody @Valid LeadsRequestDTO order) {

        LeadsResponseDTO response = leadsService.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/order")
    public ResponseEntity<LeadsPageResponseDTO> find(
            @RequestParam(value = "name", required = false) List<String> names,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        LeadsPageResponseDTO response = leadsService.find(names, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/order/{id}")
    public ResponseEntity<LeadsResponseDTO> update(@PathVariable Long id, @RequestBody LeadsRequestDTO order) {

        LeadsResponseDTO response = leadsService.update(id, order);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/cart/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        leadsService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

