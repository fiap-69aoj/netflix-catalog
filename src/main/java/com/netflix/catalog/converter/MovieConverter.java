package com.netflix.catalog.converter;

import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.entity.MovieEntity;
import com.netflix.catalog.util.RatingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter {

    @Autowired
    private RatingUtil ratingUtil;

    public MovieEntity movieToEntityConverter(final MovieRequest request) {
        return MovieEntity.builder()
            .name(request.getName())
            .image(request.getImage())
            .rating(ratingUtil.findRating(request.getRating()))
            .summary(request.getSummary())
            .releaseDate(request.getReleaseDate())
            .build();
    }

}
