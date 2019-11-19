package com.netflix.catalog.repository;

import com.netflix.catalog.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

    Optional<List<FavoriteEntity>> findByIdUser(final Long idUser);

}
