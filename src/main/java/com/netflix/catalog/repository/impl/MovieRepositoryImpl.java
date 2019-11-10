package com.netflix.catalog.repository.impl;

import com.netflix.catalog.dto.MovieWatchedByCategoryResponse;
import com.netflix.catalog.repository.custom.MovieRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MovieRepositoryImpl implements MovieRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<MovieWatchedByCategoryResponse> topMovieWatchedByCategory() {
        String sql = "SELECT " +
            "m.id as id_movie, m.name as name_movie, mc.id_category, " +
            "c.name as name_category, count(m.id) as amount " +
            "FROM movie_watched mw " +
            "INNER JOIN movie m ON mw.id_movie = m.id " +
            "INNER JOIN movie_category mc ON mc.id_movie = mw.id_movie " +
            "INNER JOIN category c ON mc.id_category = c.id " +
            "GROUP BY m.id, mc.id_category " +
            "ORDER BY amount DESC";

        Query query = manager.createNativeQuery(sql);
        List<Object[]> result = query.getResultList();

        List<MovieWatchedByCategoryResponse> moviesWatchedByCategory = new ArrayList<>();
        result.forEach(objects -> {
            MovieWatchedByCategoryResponse movieWatchedByCategory = MovieWatchedByCategoryResponse.builder()
                .idMovie((BigInteger) objects[0])
                .nameMovie((String) objects[1])
                .idCategory((BigInteger) objects[2])
                .nameCategory((String) objects[3])
                .amount((BigInteger) objects[4])
                .build();

            if (!moviesWatchedByCategory.contains(movieWatchedByCategory)) {
                moviesWatchedByCategory.add(movieWatchedByCategory);
            }
        });

        return moviesWatchedByCategory;
    }

}
