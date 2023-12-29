package com.example.hyper.repositories;

import com.example.hyper.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseRepository<CustomerEntity> {

    @Query("SELECT o FROM CustomerEntity o WHERE customerId = :customerId")
    Optional<CustomerEntity> findByCustomerId(@Param("customerId")String customerId);

}
