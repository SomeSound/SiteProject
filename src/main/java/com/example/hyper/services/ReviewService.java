package com.example.hyper.services;

import com.example.hyper.dtos.requests.ReviewRequestDTO;
import com.example.hyper.dtos.responses.ReviewResponseDTO;
import com.example.hyper.dtos.responses.pages.ReviewPageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    public ReviewResponseDTO save(ReviewRequestDTO review);

    public ReviewPageResponseDTO find(String review, Pageable pageable);

    public ReviewResponseDTO update(Long id, ReviewRequestDTO review);

    public void delete(Long id);

}
