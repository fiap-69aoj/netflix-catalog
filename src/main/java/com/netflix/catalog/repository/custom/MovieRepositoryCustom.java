package com.netflix.catalog.repository.custom;

import com.netflix.catalog.dto.MovieWatchedByCategoryResponse;

import java.util.List;

public interface MovieRepositoryCustom {

    List<MovieWatchedByCategoryResponse> topMovieWatchedByCategory();

}
