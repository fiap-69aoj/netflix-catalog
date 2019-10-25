package com.netflix.catalog.repository;

import com.netflix.catalog.entity.SerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<SerieEntity, Long> {
}
