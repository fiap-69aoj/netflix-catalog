package com.netflix.catalog.repository;

import com.netflix.catalog.entity.MovieWatchedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieWatchedRepository extends JpaRepository<MovieWatchedEntity, Long> {

    List<MovieWatchedEntity> findByIdIdUser(final Long idUser);

}
