package br.com.hyper.repositories;

import br.com.hyper.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<OrderEntity>{
    @Query("SELECT o FROM CartEntity o WHERE customerId = :customerId")
    Page<OrderEntity> findByCustomerId(@Param("customerId") Long customerId, Pageable pageable);
}
