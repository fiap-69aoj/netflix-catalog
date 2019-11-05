package com.netflix.catalog.service;

import com.netflix.catalog.converter.MovieConverter;
import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.entity.MovieEntity;
import com.netflix.catalog.exception.CatalogException;
import com.netflix.catalog.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieConverter;

    @Autowired
    private CategoryService categoryService;

    private void verifyMovieName(final String name) {
        movieRepository.findByName(name)
            .ifPresent(movie -> {
                final String message = "Movie %s already exists";
                throw new CatalogException(HttpStatus.NOT_FOUND, String.format(message, name));
            });
    }

    public MovieResponse save(final MovieRequest request) {
        final MovieEntity movieEntity = movieConverter.toMovieEntity(request);

        verifyMovieName(request.getName());
        categoryService.verifyCategoriesExists(movieEntity.getCategories());

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

    public Page<MovieResponse> findByCategory(final Long idCategory, final Pageable pageable) {
        final List<MovieEntity> moviesEntity = movieRepository.findByCategoriesId(idCategory, pageable);

        List<MovieResponse> movies = moviesEntity.stream().map(movie -> movieConverter.toMovieResponse(movie))
            .collect(Collectors.toList());

        return new PageImpl<>(movies);
    }

}
