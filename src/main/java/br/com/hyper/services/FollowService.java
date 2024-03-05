package br.com.hyper.services;

import br.com.hyper.dtos.requests.FollowRequestDTO;
import br.com.hyper.dtos.responses.FollowResponseDTO;
import br.com.hyper.dtos.responses.pages.FollowPageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowService {

    FollowResponseDTO save(FollowRequestDTO user);

    FollowPageResponseDTO find(List<String> customerId, Pageable pageable);

    FollowResponseDTO update(Long id, FollowRequestDTO follower);

    void delete(Long id);
}
