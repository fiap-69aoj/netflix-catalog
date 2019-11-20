package com.netflix.catalog.converter;

import com.netflix.catalog.dto.MovieFavoriteRequest;
import com.netflix.catalog.dto.MovieLikeRequest;
import com.netflix.catalog.dto.SerieFavoriteRequest;
import com.netflix.catalog.dto.SerieLikeRequest;
import com.netflix.catalog.entity.FavoriteEntity;
import com.netflix.catalog.entity.LikeEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LikeConverter {

    public LikeEntity movieToLikeEntity(final MovieLikeRequest movieLikeRequest) {
        return LikeEntity.builder()
            .idUser(movieLikeRequest.getIdUser())
            .idMovie(movieLikeRequest.getIdMovie())
            .date(new Date())
            .build();
    }

    public LikeEntity serieToLikeEntity(final SerieLikeRequest serieLikeRequest) {
        return LikeEntity.builder()
            .idUser(serieLikeRequest.getIdUser())
            .idSerie(serieLikeRequest.getIdSerie())
            .date(new Date())
            .build();
    }

}
