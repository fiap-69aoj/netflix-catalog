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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;

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

    private List<MovieResponse> findByLabelsLabelContaining(final String label) {
        final List<MovieEntity> moviesEntity = movieRepository.findByLabelsLabelContaining(label);

        return moviesEntity.stream().map(movieConverter::toMovieResponse)
            .collect(Collectors.toList());
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

    public List<MovieResponse> findByCategory(final Long idCategory) {
        final List<MovieEntity> moviesEntity = movieRepository.findByCategoriesId(idCategory);

        return moviesEntity.stream().map(movie -> movieConverter.toMovieResponse(movie))
            .collect(Collectors.toList());
    }

    public List<MovieResponse> findByLabel(final String query) {
        String[] queries = query.split(" ");
        List<MovieResponse> movieResponses = new ArrayList<>();

        Arrays.stream(queries)
            .forEach(label -> movieResponses.addAll(findByLabelsLabelContaining(label)));

        return movieResponses.stream()
            .collect(collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(comparingLong(MovieResponse::getId))), ArrayList::new));
    }

}
