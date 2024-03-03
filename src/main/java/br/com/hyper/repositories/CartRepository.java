package br.com.hyper.repositories;

import br.com.hyper.entities.ArtistEntity;
import br.com.hyper.entities.CartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends BaseRepository<CartEntity> {

    @Query("SELECT o FROM CartEntity o WHERE name in :names")
    Page<CartEntity> findByName(@Param("names") List<String> names,
                                  Pageable pageable);

}
