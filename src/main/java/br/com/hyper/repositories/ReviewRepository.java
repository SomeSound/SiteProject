package br.com.hyper.repositories;

import br.com.hyper.entities.CustomerEntity;
import br.com.hyper.entities.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends BaseRepository<ReviewEntity> {

    @Query("SELECT o FROM ReviewEntity o WHERE customer = :customer")
    Page<ReviewEntity> findByCustomer(@Param("customer") CustomerEntity customer, Pageable pageable);
}
