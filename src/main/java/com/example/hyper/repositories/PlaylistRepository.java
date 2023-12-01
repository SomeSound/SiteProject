package com.example.hyper.repositories;

import com.example.hyper.entities.PlaylistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends BaseRepository<PlaylistEntity> {

    @Query("SELECT o FROM PlaylistEntity o WHERE o.name = :name")
    Page<PlaylistEntity> findByName(@Param("name") String name,
                                  Pageable pageable);
}
