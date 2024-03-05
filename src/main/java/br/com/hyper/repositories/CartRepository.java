package br.com.hyper.repositories;

import br.com.hyper.entities.CartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends BaseRepository<CartEntity> {

    @Query("SELECT o FROM CartEntity o WHERE customerId in :customerId")
    Page<CartEntity> findByCustomerId(@Param("customerId") Long customerId, Pageable pageable);

}
