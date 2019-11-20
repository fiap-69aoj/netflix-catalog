package com.netflix.catalog.repository;

import com.netflix.catalog.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    Optional<List<LikeEntity>> findByIdUser(final Long idUser);

}
