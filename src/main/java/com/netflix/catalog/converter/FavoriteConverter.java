package com.netflix.catalog.converter;

import com.netflix.catalog.dto.MovieFavoriteRequest;
import com.netflix.catalog.dto.SerieFavoriteRequest;
import com.netflix.catalog.entity.FavoriteEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FavoriteConverter {

    public FavoriteEntity movieToFavoriteEntity(final MovieFavoriteRequest movieFavoriteRequest) {
        return FavoriteEntity.builder()
            .idUser(movieFavoriteRequest.getIdUser())
            .idMovie(movieFavoriteRequest.getIdMovie())
            .date(new Date())
            .build();
    }

    public FavoriteEntity serieToFavoriteEntity(final SerieFavoriteRequest serieFavoriteRequest) {
        return FavoriteEntity.builder()
            .idUser(serieFavoriteRequest.getIdUser())
            .idSerie(serieFavoriteRequest.getIdSerie())
            .date(new Date())
            .build();
    }

}
