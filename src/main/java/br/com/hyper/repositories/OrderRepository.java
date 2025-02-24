package br.com.hyper.repositories;

import br.com.hyper.entities.CustomerEntity;
import br.com.hyper.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<OrderEntity>{
    @Query("SELECT o FROM OrderEntity o WHERE customer = :customer")
    Page<OrderEntity> findByCustomer(@Param("customer") CustomerEntity customer, Pageable pageable);
}
