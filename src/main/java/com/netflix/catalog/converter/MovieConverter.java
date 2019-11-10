package com.netflix.catalog.converter;

import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.dto.MovieWatchResponse;
import com.netflix.catalog.dto.MovieWatchedRequest;
import com.netflix.catalog.dto.MovieWatchedResponse;
import com.netflix.catalog.entity.CategoryEntity;
import com.netflix.catalog.entity.MovieEntity;
import com.netflix.catalog.entity.MovieLabelEntity;
import com.netflix.catalog.entity.MovieWatchedEntity;
import com.netflix.catalog.util.RatingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieConverter {

    @Autowired
    private RatingUtil ratingUtil;

    public MovieEntity toMovieEntity(final MovieRequest request) {
        return MovieEntity.builder()
            .name(request.getName())
            .image(request.getImage())
            .rating(ratingUtil.findRating(request.getRating()))
            .summary(request.getSummary())
            .releaseDate(request.getReleaseDate())
            .labels(
                request.getLabels().stream()
                    .map(label -> MovieLabelEntity.builder()
                        .label(label)
                        .build())
                    .collect(Collectors.toList())
            )
            .categories(
                request.getCategories().stream()
                    .map(category -> CategoryEntity.builder()
                        .id(category.getId())
                        .build())
                    .collect(Collectors.toList()))
            .build();
    }

    public MovieResponse toMovieResponse(final MovieEntity entity) {
        return MovieResponse.builder()
            .id(entity.getId())
            .name(entity.getName())
            .labels(
                entity.getLabels().stream()
                    .map(MovieLabelEntity::getLabel)
                    .collect(Collectors.toList())
            )
            .categories(
                entity.getCategories().stream()
                    .map(CategoryEntity::getDescription)
                    .collect(Collectors.toList())
            )
            .build();
    }

    public MovieWatchedEntity toMovieWatchedEntity(final MovieWatchedRequest request) {
        MovieWatchedEntity.MovieWatchedEntityPK movieWatchedEntityPK = MovieWatchedEntity.MovieWatchedEntityPK.builder()
            .idUser(request.getIdUser())
            .movie(MovieEntity.builder()
                .id(request.getIdMovie())
                .build())
            .build();

        return MovieWatchedEntity.builder()
            .id(movieWatchedEntityPK)
            .date(new Date())
            .build();
    }

    public MovieWatchedResponse toMovieWatchedResponse(final List<MovieWatchedEntity> moviesWatched) {
        List<MovieWatchResponse> movies = new ArrayList<>();
        moviesWatched.forEach(movieWatched -> movies.add(
            MovieWatchResponse.builder()
                .date(movieWatched.getDate())
                .movie(toMovieResponse(movieWatched.getId().getMovie()))
                .build()
        ));

        return MovieWatchedResponse.builder()
            .idUser(moviesWatched.get(0).getId().getIdUser())
            .movies(movies)
            .build();
    }

}
