package br.com.hyper.repositories;

import br.com.hyper.entities.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseRepository<CustomerEntity> {

    @Query("SELECT o FROM CustomerEntity o WHERE customerId = :customerId")
    Optional<CustomerEntity> findByCustomerId(@Param("customerId")String customerId);

}
