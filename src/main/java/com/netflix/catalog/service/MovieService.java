package com.netflix.catalog.service;

import com.netflix.catalog.converter.MovieConverter;
import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.entity.CategoryEntity;
import com.netflix.catalog.entity.MovieEntity;
import com.netflix.catalog.exception.CatalogException;
import com.netflix.catalog.repository.CategoryRepository;
import com.netflix.catalog.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    public MovieResponse save(final MovieRequest request) {
        final MovieEntity movieEntity = movieConverter.toMovieEntity(request);

        verifyMovieName(request.getName());
        verifyCategories(movieEntity.getCategories());

        final MovieEntity movie = movieRepository.save(movieEntity);
        return movieConverter.toMovieResponse(movie);
    }

    public Page<MovieResponse> findAll(final Pageable pageable) {
        final Page<MovieEntity> movies = movieRepository.findAll(pageable);
        return movies.map(movie -> movieConverter.toMovieResponse(movie));
    }

    public MovieResponse findById(final Long id) {
        final MovieEntity movie = movieRepository.findById(id)
            .orElseThrow(() -> new CatalogException(HttpStatus.NOT_FOUND, "Movie not found"));

        return movieConverter.toMovieResponse(movie);
    }

    private void verifyMovieName(final String name) {
        movieRepository.findByName(name)
            .ifPresent(movie -> {
                final String message = "Movie %s already exists";
                throw new CatalogException(HttpStatus.NOT_FOUND, String.format(message, name));
            });
    }

    private void verifyCategories(final List<CategoryEntity> categories) {
        categories.stream()
            .filter(category -> !categoryRepository.existsById(category.getId()))
            .findAny()
            .ifPresent(category -> {
                final String message = "Category %d not found";
                throw new CatalogException(HttpStatus.NOT_FOUND, String.format(message, category.getId()));
            });
    }

}
