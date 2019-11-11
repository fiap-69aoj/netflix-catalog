package com.netflix.catalog.repository;

import com.netflix.catalog.entity.SerieEntity;
import com.netflix.catalog.repository.custom.SerieRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<SerieEntity, Long>, SerieRepositoryCustom {

    Optional<SerieEntity> findByName(final String name);
    List<SerieEntity> findByCategoriesId(final Long idCategory);
    List<SerieEntity> findByLabelsLabelContaining(final String label);


}
