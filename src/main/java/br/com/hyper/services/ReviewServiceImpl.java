package br.com.hyper.services;

import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.requests.ReviewRequestDTO;
import br.com.hyper.dtos.responses.ReviewResponseDTO;
import br.com.hyper.dtos.responses.pages.ReviewPageResponseDTO;
import br.com.hyper.entities.CustomerEntity;
import br.com.hyper.entities.ReviewEntity;
import br.com.hyper.exceptions.CustomerException;
import br.com.hyper.exceptions.InvalidReviewDataException;
import br.com.hyper.exceptions.ReviewNotFoundException;
import br.com.hyper.repositories.CustomerRepository;
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

    private final ReviewRepository reviewRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final CustomerRepository customerRepository;

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

        CustomerEntity customer = findByEmailOrThrowUserDataNotFoundException(email);

        if(email != null){
            reviewEntities = reviewRepository.findByCustomer(customer, pageable);
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

        modelMapper.map(reviewCurrent, ReviewResponseDTO.class);

        reviewRepository.delete(reviewCurrent);
    }
    private ReviewEntity findByIdOrThrowReviewDataNotFoundException(Long id) {
        return reviewRepository.findById(id).orElseThrow(
                () -> new ReviewNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

    private CustomerEntity findByEmailOrThrowUserDataNotFoundException(String email) {
        return customerRepository.findByEmail(email).orElseThrow(
                () -> new CustomerException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}

