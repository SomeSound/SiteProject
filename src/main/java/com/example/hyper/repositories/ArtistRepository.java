package com.example.hyper.repositories;

import com.example.hyper.entities.ArtistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends BaseRepository<ArtistEntity> {

    @Query("SELECT o FROM ArtistEntity o WHERE o.username in :usernames")
    Page<ArtistEntity> findByUsername(@Param("usernames") List<String> usernames,
                                      Pageable pageable);
    @Query("SELECT o FROM ArtistEntity o WHERE name in :names")
    Page<ArtistEntity> findByName(@Param("names") List<String> names,
                                  Pageable pageable);

}
