package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.ReviewRequestDTO;
import com.example.hyper.dtos.responses.ReviewResponseDTO;
import com.example.hyper.dtos.responses.pages.ReviewPageResponseDTO;
import com.example.hyper.entities.ReviewEntity;
import com.example.hyper.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReviewResponseDTO save(ReviewRequestDTO review) {
        ReviewEntity reviewEntity;
        try {
            reviewEntity = modelMapper.map(review, ReviewEntity.class);

            reviewEntity = reviewRepository.save(reviewEntity);

            return modelMapper.map(reviewEntity, ReviewResponseDTO.class);

        } catch (DataIntegrityViolationException e) {
            throw new InvalidReviewDataException(ErrorCodes.INVALID_REVIEW_ERROR,
                    ErrorCodes.INVALID_REVIEW_ERROR.getMessage());
        }
    }

    @Override
    public ReviewPageResponseDTO find(String review, Pageable pageable){

    }

    @Override
    public ReviewResponseDTO update(Long id, ReviewRequestDTO review) {
    }

    @Override
    public void delete(Long id) {

    }
}
