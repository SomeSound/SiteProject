package com.example.hyper.services;

import com.example.hyper.dtos.requests.FollowRequestDTO;
import com.example.hyper.dtos.responses.FollowResponseDTO;
import com.example.hyper.dtos.responses.pages.FollowPageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowService {

    public FollowResponseDTO save(FollowRequestDTO user);

    public FollowPageResponseDTO find(List<String> names, Pageable pageable);

    public FollowResponseDTO update(Long id, FollowRequestDTO follower);

    public void delete(Long id);
}
