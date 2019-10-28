package com.netflix.catalog.converter;

import com.netflix.catalog.dto.SerieRequest;
import com.netflix.catalog.dto.SerieResponse;
import com.netflix.catalog.entity.CategoryEntity;
import com.netflix.catalog.entity.MovieLabelEntity;
import com.netflix.catalog.entity.SerieEntity;
import com.netflix.catalog.entity.SerieLabelEntity;
import com.netflix.catalog.util.RatingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SerieConverter {

    @Autowired
    private RatingUtil ratingUtil;

    public SerieEntity toSerieEntity(final SerieRequest request) {
        return SerieEntity.builder()
            .name(request.getName())
            .image(request.getImage())
            .rating(ratingUtil.findRating(request.getRating()))
            .summary(request.getSummary())
            .releaseDate(request.getReleaseDate())
            .labels(
                request.getLabels().stream()
                    .map(label -> SerieLabelEntity.builder()
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

    public SerieResponse toSerieResponse(final SerieEntity entity) {
        return SerieResponse.builder()
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
