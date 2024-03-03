package br.com.hyper.controllers;

import br.com.hyper.services.RecordService;
import br.com.hyper.dtos.requests.RecordRequestDTO;
import br.com.hyper.dtos.responses.RecordResponseDTO;
import br.com.hyper.dtos.responses.pages.RecordPageReponseDTO;
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
public class RecordController  {
    @Autowired
    private final RecordService recordService;

    @PostMapping(value = "/record")
    public ResponseEntity<RecordResponseDTO> save(
            @RequestBody @Valid RecordRequestDTO record) {

        RecordResponseDTO response = recordService.save(record);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/record")
    public ResponseEntity<RecordPageReponseDTO> find(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        RecordPageReponseDTO response = recordService.find(name, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/record/{id}")
    public ResponseEntity<RecordResponseDTO> update(@PathVariable Long id, @RequestBody RecordRequestDTO record) {

        RecordResponseDTO response = recordService.update(id, record);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/record/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        recordService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
