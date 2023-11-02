package com.example.SiteProject.SiteProject.repositories;

import com.example.SiteProject.SiteProject.entities.MusicEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends BaseRepository<MusicEntity> {

    MusicEntity findByName(String name);
}
