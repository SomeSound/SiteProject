package com.example.hyper.repositories;

import com.example.hyper.entities.CollectionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollectionRepository extends BaseRepository<CollectionEntity> {

    @Query("SELECT o FROM CollectionEntity o WHERE name in: names")
    Page<CollectionEntity> findByName(@Param("name") String name,
                                    Pageable pageable);

}
