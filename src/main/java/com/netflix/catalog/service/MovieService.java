package com.netflix.catalog.service;

import com.netflix.catalog.converter.FavoriteConverter;
import com.netflix.catalog.converter.MovieConverter;
import com.netflix.catalog.dto.MovieFavoriteRequest;
import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.dto.MovieWatchResponse;
import com.netflix.catalog.dto.MovieWatchedByCategoryResponse;
import com.netflix.catalog.dto.MovieWatchedRequest;
import com.netflix.catalog.dto.MovieWatchedResponse;
import com.netflix.catalog.entity.FavoriteEntity;
import com.netflix.catalog.entity.MovieEntity;
import com.netflix.catalog.entity.MovieWatchedEntity;
import com.netflix.catalog.exception.CatalogException;
import com.netflix.catalog.repository.FavoriteRepository;
import com.netflix.catalog.repository.MovieRepository;
import com.netflix.catalog.repository.MovieWatchedRepository;
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

import static java.util.Collections.singletonList;
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

    @Autowired
    private MovieWatchedRepository movieWatchedRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteConverter favoriteConverter;

    private void verifyMovieName(final String name) {
        movieRepository.findByName(name)
            .ifPresent(movie -> {
                final String message = "Movie %s already exists";
                throw new CatalogException(HttpStatus.NOT_FOUND, String.format(message, name));
            });
    }

    private List<MovieResponse> findByLabelsLabelContaining(final String label) {
        final List<MovieEntity> movieEntities = movieRepository.findByLabelsLabelContaining(label);

        return movieEntities.stream().map(movieConverter::toMovieResponse)
            .collect(Collectors.toList());
    }

    private MovieEntity findMovieById(final Long idMovie) {
        return movieRepository.findById(idMovie)
            .orElseThrow(() -> new CatalogException(HttpStatus.NOT_FOUND, "Movie not found"));
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

    public MovieResponse findById(final Long idMovie) {
        return movieConverter.toMovieResponse(findMovieById(idMovie));
    }

    public List<MovieResponse> findByCategory(final Long idCategory) {
        final List<MovieEntity> movieEntities = movieRepository.findByCategoriesId(idCategory);

        return movieEntities.stream().map(movieConverter::toMovieResponse)
            .collect(Collectors.toList());
    }

    public List<MovieResponse> findByLabel(final String query) {
        final String[] queries = query.split(" ");
        final List<MovieResponse> movies = new ArrayList<>();

        Arrays.stream(queries)
            .forEach(label -> movies.addAll(findByLabelsLabelContaining(label)));

        return movies.stream()
            .collect(collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(comparingLong(MovieResponse::getId))), ArrayList::new));
    }

    public MovieWatchedResponse watch(final MovieWatchedRequest request) {
        final MovieWatchedEntity movieWatchedEntity = movieConverter.toMovieWatchedEntity(request);
        final MovieResponse movie = findById(movieWatchedEntity.getId().getMovie().getId());
        final MovieWatchedEntity movieWatched = movieWatchedRepository.save(movieWatchedEntity);

        return MovieWatchedResponse.builder()
            .idUser(request.getIdUser())
            .movies(singletonList(
                MovieWatchResponse.builder()
                    .movie(movie)
                    .date(movieWatched.getDate())
                    .build()))
            .build();
    }

    public MovieWatchedResponse watched(final Long idUser) {
        final List<MovieWatchedEntity> movieWatchedEntities = movieWatchedRepository.findByIdIdUser(idUser);
        return movieConverter.toMovieWatchedResponse(movieWatchedEntities);
    }

    public List<MovieWatchedByCategoryResponse> topMovieWatchedByCategory() {
        return movieRepository.topMovieWatchedByCategory();
    }

    public void sendToFavorites(final MovieFavoriteRequest movieFavoriteRequest) {
        final FavoriteEntity favoriteEntity = favoriteConverter.toFavoriteEntity(movieFavoriteRequest);
        findById(favoriteEntity.getIdMovie());
        favoriteRepository.save(favoriteEntity);
    }

}
