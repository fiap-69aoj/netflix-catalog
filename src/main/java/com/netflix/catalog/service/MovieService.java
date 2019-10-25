package com.netflix.catalog.service;

import com.netflix.catalog.converter.MovieConverter;
import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.entity.MovieEntity;
import com.netflix.catalog.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
