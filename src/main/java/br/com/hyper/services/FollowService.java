package br.com.hyper.services;

import br.com.hyper.dtos.requests.FollowRequestDTO;
import br.com.hyper.dtos.responses.FollowResponseDTO;
import br.com.hyper.dtos.responses.pages.FollowPageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface FollowService {

    FollowResponseDTO save(FollowRequestDTO user);

    FollowPageResponseDTO find(String email, Pageable pageable);

    FollowResponseDTO update(Long id, FollowRequestDTO follower);

    void delete(Long id);
}
