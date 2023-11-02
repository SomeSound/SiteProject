package com.example.SiteProject.SiteProject.repositories;

import com.example.SiteProject.SiteProject.entities.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends BaseRepository<MusicEntity> {

    @Query("SELECT o FROM MusicEntity o WHERE genre in :genres " +
            "AND (:name is NULL or o.name = :name) ")
    Page<MusicEntity> findByGenre(@Param("genres") List<String> genres,
                                  @Param("name") String name,
                                  Pageable pageable);
}
