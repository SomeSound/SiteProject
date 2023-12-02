package com.example.hyper.controllers;

import com.example.hyper.dtos.UserDTO;
import com.example.hyper.dtos.responses.UserPageResponseDTO;
import com.example.hyper.dtos.responses.UserResponseDTO;
import com.example.hyper.services.UserService;
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
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping(value = "/user")
    public ResponseEntity<UserResponseDTO> save(
            @RequestBody @Valid UserDTO user){

        UserResponseDTO response = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping(value = "/user")
    public ResponseEntity<UserPageResponseDTO> find(
            @RequestParam(value = "names", required = false) List<String> names,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "sort", defaultValue = "UNSORT", required = false) String sort,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);

        UserPageResponseDTO response = userService.find(names, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserDTO user) {

        UserResponseDTO response = userService.update(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        userService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
