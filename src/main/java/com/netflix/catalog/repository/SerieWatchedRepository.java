package com.netflix.catalog.repository;

import com.netflix.catalog.entity.MovieWatchedEntity;
import com.netflix.catalog.entity.SerieWatchedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieWatchedRepository extends JpaRepository<SerieWatchedEntity, Long> {

    List<SerieWatchedEntity> findByIdIdUser(final Long idUser);

}
