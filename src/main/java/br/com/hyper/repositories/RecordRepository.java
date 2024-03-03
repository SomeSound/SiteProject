package br.com.hyper.repositories;

import br.com.hyper.entities.RecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordRepository extends BaseRepository<RecordEntity> {
    @Query("SELECT o FROM PlaylistEntity o WHERE o.name = :name")
    Page<RecordEntity> findByName(@Param("name") String name,
                                  Pageable pageable);
}
