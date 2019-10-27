package com.netflix.catalog.converter;

import com.netflix.catalog.dto.SerieRequest;
import com.netflix.catalog.dto.SerieResponse;
import com.netflix.catalog.entity.SerieEntity;
import com.netflix.catalog.util.RatingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            .build();
    }

    public SerieResponse toSerieResponse(final SerieEntity entity) {
        return SerieResponse.builder()
            .id(entity.getId())
            .name(entity.getName())
            .build();
    }



}
