package com.netflix.catalog.service;

import com.netflix.catalog.converter.MovieConverter;
import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.entity.MovieEntity;
import com.netflix.catalog.exception.CatalogException;
import com.netflix.catalog.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieConverter;

    public MovieResponse save(final MovieRequest request) {
        final MovieEntity movieEntity = movieConverter.movieToEntityConverter(request);

        final MovieEntity movie = movieRepository.save(movieEntity);

        return MovieResponse.builder()
            .id(movie.getId())
            .build();
    }

    public Page<MovieResponse> findAll(final Pageable pageable) {
        final Page<MovieEntity> movies = movieRepository.findAll(pageable);

        return movies.map(m -> MovieResponse.builder()
            .id(m.getId())
            .name(m.getName())
            .build()
        );
    }

    public MovieResponse findById(final Long id) {
        final MovieEntity movie = movieRepository.findById(id)
            .orElseThrow(() -> new CatalogException(HttpStatus.NOT_FOUND, "Movie not found"));

        return MovieResponse.builder()
            .id(movie.getId())
            .name(movie.getName())
            .build();
    }

}
