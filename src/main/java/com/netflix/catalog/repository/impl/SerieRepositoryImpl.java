package com.netflix.catalog.repository.impl;

import com.netflix.catalog.dto.SerieWatchedByCategoryResponse;
import com.netflix.catalog.repository.custom.SerieRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SerieRepositoryImpl implements SerieRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<SerieWatchedByCategoryResponse> topSerieWatchedByCategory() {
        String sql = "SELECT " +
            "s.id as id_serie, s.name as name_serie, sc.id_category, " +
            "c.name as name_category, count(s.id) as amount " +
            "FROM serie_watched sw " +
            "INNER JOIN serie s ON sw.id_serie = s.id " +
            "INNER JOIN serie_category sc ON sc.id_serie = sw.id_serie " +
            "INNER JOIN category c ON sc.id_category = c.id " +
            "GROUP BY s.id, sc.id_category " +
            "ORDER BY amount DESC";

        Query query = manager.createNativeQuery(sql);
        List<Object[]> result = query.getResultList();

        List<SerieWatchedByCategoryResponse> seriesWatchedByCategory = new ArrayList<>();
        result.forEach(objects -> {
            SerieWatchedByCategoryResponse serieWatchedByCategory = SerieWatchedByCategoryResponse.builder()
                .idSerie((BigInteger) objects[0])
                .nameSerie((String) objects[1])
                .idCategory((BigInteger) objects[2])
                .nameCategory((String) objects[3])
                .amount((BigInteger) objects[4])
                .build();

            if (!seriesWatchedByCategory.contains(serieWatchedByCategory)) {
                seriesWatchedByCategory.add(serieWatchedByCategory);
            }
        });

        return seriesWatchedByCategory;
    }

}
