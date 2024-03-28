package br.com.hyper.controllers;

import br.com.hyper.services.FollowService;
import br.com.hyper.dtos.requests.FollowRequestDTO;
import br.com.hyper.dtos.responses.FollowResponseDTO;
import br.com.hyper.dtos.responses.pages.FollowPageResponseDTO;
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
public class FollowController {

    @Autowired
    private final FollowService followService;

    @PostMapping(value = "/follow")
    public ResponseEntity<FollowResponseDTO> save(
            @RequestBody @Valid FollowRequestDTO follow) {

        FollowResponseDTO response = followService.save(follow);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/follow")
    public ResponseEntity<FollowPageResponseDTO> find(
            @RequestParam(value = "email", required = false) List<String> email,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        FollowPageResponseDTO response = followService.find(email, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/follow/{id}")
    public ResponseEntity<FollowResponseDTO> update(@PathVariable Long id, @RequestBody FollowRequestDTO follow) {

        FollowResponseDTO response = followService.update(id, follow);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/follow/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        followService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
