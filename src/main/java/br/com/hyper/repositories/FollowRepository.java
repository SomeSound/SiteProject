package br.com.hyper.repositories;

import br.com.hyper.entities.FollowEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends BaseRepository<FollowEntity>{

    @Query("SELECT o FROM FollowEntity o WHERE customerId in :customerIds")
    Page<FollowEntity> findByCustomerId(@Param("customerIds") List<String> customerIds, Pageable pageable);
}
