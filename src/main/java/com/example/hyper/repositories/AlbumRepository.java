package com.example.hyper.repositories;

import com.example.hyper.entities.AlbumEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends BaseRepository<AlbumEntity> {

    @Query("SELECT o FROM AlbumEntity o WHERE o.name = :name")
    Page<AlbumEntity> findByName(@Param("name") String name,
                                   Pageable pageable);
}
