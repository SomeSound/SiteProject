package com.example.hyper.repositories;

import com.example.hyper.entities.CollectionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository {
    @Query("SELECT o FROM ReviewEntity o WHERE review in: reviews")
    Page<CollectionEntity> findByReview(@Param("review") String review,
                                      Pageable pageable);
}
