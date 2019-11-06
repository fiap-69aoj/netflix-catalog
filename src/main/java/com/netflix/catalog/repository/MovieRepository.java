package com.netflix.catalog.repository;

import com.netflix.catalog.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> findByName(final String name);
    List<MovieEntity> findByCategoriesId(final Long idCategory);
    List<MovieEntity> findByLabelsLabelContaining(final String label);

}
