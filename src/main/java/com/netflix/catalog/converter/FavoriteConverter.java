package com.netflix.catalog.converter;

import com.netflix.catalog.dto.MovieFavoriteRequest;
import com.netflix.catalog.entity.FavoriteEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FavoriteConverter {

    public FavoriteEntity toFavoriteEntity(final MovieFavoriteRequest movieFavoriteRequest) {
        return FavoriteEntity.builder()
            .idUser(movieFavoriteRequest.getIdUser())
            .idMovie(movieFavoriteRequest.getIdMovie())
            .date(new Date())
            .build();
    }

}
