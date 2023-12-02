package com.example.hyper.services;

import com.example.hyper.dtos.UserDTO;
import com.example.hyper.dtos.responses.UserPageResponseDTO;
import com.example.hyper.dtos.responses.UserResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public UserResponseDTO save(UserDTO user);

    public UserPageResponseDTO find(List<String> names, Pageable pageable);

    public UserResponseDTO update(Long id, UserDTO user);

    public void delete(Long id);
}
