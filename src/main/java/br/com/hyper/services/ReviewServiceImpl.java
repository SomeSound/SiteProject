package br.com.hyper.services;

import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.requests.ReviewRequestDTO;
import br.com.hyper.dtos.responses.ReviewResponseDTO;
import br.com.hyper.dtos.responses.pages.ReviewPageResponseDTO;
import br.com.hyper.entities.ReviewEntity;
import br.com.hyper.exceptions.InvalidReviewDataException;
import br.com.hyper.exceptions.ReviewNotFoundException;
import br.com.hyper.repositories.ReviewRepository;
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
    public ReviewPageResponseDTO find(String email, Pageable pageable) {

        Page<ReviewEntity> reviewEntities;

        if(email != null){
            reviewEntities = reviewRepository.findByEmail(email, pageable);
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

