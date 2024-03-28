package br.com.hyper.services;

import br.com.hyper.dtos.requests.ReviewRequestDTO;
import br.com.hyper.dtos.responses.ReviewResponseDTO;
import br.com.hyper.dtos.responses.pages.ReviewPageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    ReviewResponseDTO save(ReviewRequestDTO review);

    ReviewPageResponseDTO find(String email, Pageable pageable);

    ReviewResponseDTO update(Long id, ReviewRequestDTO review);

    void delete(Long id);

}
