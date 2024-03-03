package br.com.hyper.repositories;

import br.com.hyper.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {

    @Query("SELECT o FROM UserEntity o WHERE name in: names")
    Page<UserEntity> findByName(@Param("names")List<String> names,
                                Pageable pageable);

}
