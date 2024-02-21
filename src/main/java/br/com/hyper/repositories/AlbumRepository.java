package br.com.hyper.repositories;

import br.com.hyper.entities.AlbumEntity;
import br.com.hyper.entities.ArtistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends BaseRepository<AlbumEntity> {

    @Query("SELECT o FROM AlbumEntity o WHERE name in :names")
    Page<ArtistEntity> findByName(@Param("names") List<String> names,
                                  Pageable pageable);

}
