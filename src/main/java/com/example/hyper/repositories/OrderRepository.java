package com.example.hyper.repositories;

import com.example.hyper.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<OrderEntity>{
    @Query("SELECT o FROM CartEntity o WHERE name in :names")
    Page<OrderEntity> findByName(@Param("names") List<String> names,
                                 Pageable pageable);
}
