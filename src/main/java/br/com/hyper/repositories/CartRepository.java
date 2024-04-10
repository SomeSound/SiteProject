package br.com.hyper.repositories;

import br.com.hyper.entities.CartEntity;
import br.com.hyper.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends BaseRepository<CartEntity> {

    @Query("SELECT o FROM CartEntity o WHERE customer in :customer")
    Page<CartEntity> findByCustomer(@Param("customer") CustomerEntity customer, Pageable pageable);

}
