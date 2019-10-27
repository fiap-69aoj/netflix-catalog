package com.netflix.catalog.converter;

import com.netflix.catalog.dto.CategoryRequest;
import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.entity.CategoryEntity;
import com.netflix.catalog.entity.MovieEntity;
import com.netflix.catalog.entity.MovieLabelEntity;
import com.netflix.catalog.util.RatingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                    .map(l -> l.getLabel())
                    .collect(Collectors.toList())
            )
            .categories(
                entity.getCategories().stream()
                    .map(c -> c.getDescription())
                    .collect(Collectors.toList())
            )
            .build();
    }

}
