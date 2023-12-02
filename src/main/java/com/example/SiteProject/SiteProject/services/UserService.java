package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.dtos.UserDTO;
import com.example.SiteProject.SiteProject.dtos.responses.UserPageResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.UserResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public UserResponseDTO save(UserDTO user);

    public UserPageResponseDTO find(List<String> names, Pageable pageable);

    public UserResponseDTO update(Long id, UserDTO user);

    public void delete(Long id);
}
