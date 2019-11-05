package com.netflix.catalog.repository;

import com.netflix.catalog.entity.SerieEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<SerieEntity, Long> {

    Optional<SerieEntity> findByName(final String name);
    List<SerieEntity> findByCategoriesId(final Long idCategory, Pageable pageable);

}
