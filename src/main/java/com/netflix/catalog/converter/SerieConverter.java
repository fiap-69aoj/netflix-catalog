package com.netflix.catalog.converter;

import com.netflix.catalog.dto.SerieRequest;
import com.netflix.catalog.dto.SerieResponse;
import com.netflix.catalog.dto.SerieWatchResponse;
import com.netflix.catalog.dto.SerieWatchedRequest;
import com.netflix.catalog.dto.SerieWatchedResponse;
import com.netflix.catalog.entity.CategoryEntity;
import com.netflix.catalog.entity.SerieEntity;
import com.netflix.catalog.entity.SerieLabelEntity;
import com.netflix.catalog.entity.SerieWatchedEntity;
import com.netflix.catalog.util.RatingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
                    .map(SerieLabelEntity::getLabel)
                    .collect(Collectors.toList())
            )
            .categories(
                entity.getCategories().stream()
                    .map(CategoryEntity::getName)
                    .collect(Collectors.toList())
            )
            .build();
    }

    public SerieWatchedEntity toSerieWatchedEntity(final SerieWatchedRequest request) {
        SerieWatchedEntity.SerieWatchedEntityPK serieWatchedEntityPK = SerieWatchedEntity.SerieWatchedEntityPK.builder()
            .idUser(request.getIdUser())
            .serie(SerieEntity.builder()
                .id(request.getIdSerie())
                .build())
            .build();

        return SerieWatchedEntity.builder()
            .id(serieWatchedEntityPK)
            .date(new Date())
            .build();
    }

    public SerieWatchedResponse toSerieWatchedResponse(final List<SerieWatchedEntity> seriesWatched) {
        List<SerieWatchResponse> series = new ArrayList<>();
        seriesWatched.forEach(serieWatched -> series.add(
            SerieWatchResponse.builder()
                .date(serieWatched.getDate())
                .serie(toSerieResponse(serieWatched.getId().getSerie()))
                .build()
        ));

        return SerieWatchedResponse.builder()
            .idUser(seriesWatched.get(0).getId().getIdUser())
            .series(series)
            .build();
    }

}
