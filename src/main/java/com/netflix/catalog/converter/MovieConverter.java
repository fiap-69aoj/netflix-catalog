package com.netflix.catalog.converter;

import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.entity.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MovieConverter {

    private final List<String> ratings = Arrays.asList("L", "10", "12", "14", "16", "18");

    public MovieEntity movieToEntityConverter(final MovieRequest request) {
        return MovieEntity.builder()
            .name(request.getName())
            .image(request.getImage())
            .rating(findRating(request.getRating()))
            .summary(request.getSummary())
            .releaseDate(request.getReleaseDate())
            .build();
    }

    private String findRating(String rating) {
        return ratings.stream()
                .filter(r -> r.equals(rating))
                .findAny()
                .orElse(null);
    }

}
