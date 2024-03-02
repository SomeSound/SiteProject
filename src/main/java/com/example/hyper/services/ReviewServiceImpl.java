package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.ReviewRequestDTO;
import com.example.hyper.dtos.responses.ReviewResponseDTO;
import com.example.hyper.dtos.responses.pages.ReviewPageResponseDTO;
import com.example.hyper.entities.ReviewEntity;
import com.example.hyper.exceptions.InvalidReviewDataException;
import com.example.hyper.exceptions.ReviewNotFoundException;
import com.example.hyper.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

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
            throw new InvalidReviewDataException(ErrorCodes.DUPLICATED_DATA,
                    ErrorCodes.DUPLICATED_DATA.getMessage());
        }
    }

    @Override
    public ReviewPageResponseDTO find(String name, Pageable pageable) {

        Page<ReviewEntity> reviewEntities;

        if(name != null){
            reviewEntities = reviewRepository.findByName(name, pageable);
        } else {
            reviewEntities = reviewRepository.findAll(pageable);
        }
        return modelMapper.map(reviewEntities, ReviewPageResponseDTO.class);
    }

    @Override
    public ReviewResponseDTO update(Long id, ReviewRequestDTO review) {
        ReviewEntity reviewCurrent = findByIdOrThrowReviewDataNotFoundException(id);

        reviewCurrent.setScore(review.getScore());

        reviewRepository.save(reviewCurrent);

        return modelMapper.map(reviewCurrent, ReviewResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        ReviewEntity reviewCurrent = findByIdOrThrowReviewDataNotFoundException(id);

        ReviewResponseDTO response = modelMapper.map(reviewCurrent, ReviewResponseDTO.class);
        reviewRepository.delete(reviewCurrent);
    }
    private ReviewEntity findByIdOrThrowReviewDataNotFoundException(Long id) {
        return reviewRepository.findById(id).orElseThrow(
                () -> new ReviewNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}

