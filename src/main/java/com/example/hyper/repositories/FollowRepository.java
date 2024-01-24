package com.example.hyper.repositories;

import com.example.hyper.entities.FollowEntity;
import com.example.hyper.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends BaseRepository<FollowEntity>{

    @Query("SELECT o FROM FollowEntity o WHERE name in :names")
    Page<FollowEntity> findByName(@Param("names") List<String> names,
                                  Pageable pageable);
}
