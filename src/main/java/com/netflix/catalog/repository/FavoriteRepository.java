package com.netflix.catalog.repository;

import com.netflix.catalog.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
}
