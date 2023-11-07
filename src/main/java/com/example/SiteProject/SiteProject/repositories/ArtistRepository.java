package com.example.SiteProject.SiteProject.repositories;

import com.example.SiteProject.SiteProject.entities.ArtistEntity;
import com.example.SiteProject.SiteProject.entities.MusicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends BaseRepository<ArtistEntity> {

    @Query("SELECT o FROM ArtistEntity o WHERE genre in :names")
    Page<ArtistEntity> findByName(@Param("names") List<String> names,
                                  Pageable pageable);

}
