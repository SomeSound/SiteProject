package br.com.hyper.repositories;

import br.com.hyper.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseRepository<CustomerEntity> {

    @Query("SELECT o FROM CustomerEntity o WHERE customerId = :customerId")
    Optional<CustomerEntity> findByCustomerId(@Param("customerId")String customerId);

    @Query("SELECT o FROM CustomerEntity o WHERE name in: names")
    Page<CustomerEntity> findByName(@Param("names")List<String> names,
                                    Pageable pageable);

}
