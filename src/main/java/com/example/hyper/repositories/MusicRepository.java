package com.example.hyper.repositories;

import com.example.hyper.entities.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends BaseRepository<MusicEntity> {

    @Query("SELECT o FROM MusicEntity o WHERE genre in :genres")
    Page<MusicEntity> findByGenre(@Param("genres") List<String> genres,
                                  Pageable pageable);
}
