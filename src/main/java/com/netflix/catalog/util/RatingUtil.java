package com.netflix.catalog.util;

import com.netflix.catalog.exception.CatalogException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RatingUtil {

    private final List<String> ratings = Arrays.asList("L", "10", "12", "14", "16", "18");

    public String findRating(String rating) {
        return ratings.stream()
            .filter(r -> r.equals(rating))
            .findAny()
            .orElseThrow(() -> new CatalogException(HttpStatus.NOT_FOUND, "Rating not found"));
    }

}
