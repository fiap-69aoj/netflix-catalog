package com.netflix.catalog.repository.custom;

import com.netflix.catalog.dto.SerieWatchedByCategoryResponse;

import java.util.List;

public interface SerieRepositoryCustom {

    List<SerieWatchedByCategoryResponse> topSerieWatchedByCategory();

}
