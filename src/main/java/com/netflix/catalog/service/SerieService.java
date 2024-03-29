package com.netflix.catalog.service;

import com.netflix.catalog.converter.FavoriteConverter;
import com.netflix.catalog.converter.LikeConverter;
import com.netflix.catalog.converter.SerieConverter;
import com.netflix.catalog.dto.SerieFavoriteRequest;
import com.netflix.catalog.dto.SerieLikeRequest;
import com.netflix.catalog.dto.SerieRequest;
import com.netflix.catalog.dto.SerieResponse;
import com.netflix.catalog.dto.SerieWatchResponse;
import com.netflix.catalog.dto.SerieWatchedByCategoryResponse;
import com.netflix.catalog.dto.SerieWatchedRequest;
import com.netflix.catalog.dto.SerieWatchedResponse;
import com.netflix.catalog.entity.FavoriteEntity;
import com.netflix.catalog.entity.LikeEntity;
import com.netflix.catalog.entity.SerieEntity;
import com.netflix.catalog.entity.SerieWatchedEntity;
import com.netflix.catalog.exception.CatalogException;
import com.netflix.catalog.repository.FavoriteRepository;
import com.netflix.catalog.repository.LikeRepository;
import com.netflix.catalog.repository.SerieRepository;
import com.netflix.catalog.repository.SerieWatchedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SerieConverter serieConverter;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SerieWatchedRepository serieWatchedRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteConverter favoriteConverter;

    @Autowired
    private LikeConverter likeConverter;

    @Autowired
    private LikeRepository likeRepository;

    private void verifySerieName(final String name) {
        serieRepository.findByName(name)
            .ifPresent(serie -> {
                final String message = "Serie %s already exists";
                throw new CatalogException(HttpStatus.NOT_FOUND, String.format(message, name));
            });
    }

    private List<SerieResponse> findByLabelsLabelContaining(final String label) {
        final List<SerieEntity> seriesEntity = serieRepository.findByLabelsLabelContaining(label);

        return seriesEntity.stream().map(serieConverter::toSerieResponse)
            .collect(Collectors.toList());
    }

    public SerieResponse save(final SerieRequest request) {
        final SerieEntity serieEntity = serieConverter.toSerieEntity(request);

        verifySerieName(request.getName());
        categoryService.verifyCategoriesExists(serieEntity.getCategories());

        final SerieEntity serie = serieRepository.save(serieEntity);
        return serieConverter.toSerieResponse(serie);
    }

    public Page<SerieResponse> findAll(final Pageable pageable) {
        final Page<SerieEntity> series = serieRepository.findAll(pageable);
        return series.map(serie -> serieConverter.toSerieResponse(serie));
    }

    public SerieResponse findById(final Long id) {
        final SerieEntity serie = serieRepository.findById(id)
            .orElseThrow(() -> new CatalogException(HttpStatus.NOT_FOUND, "Serie not found"));

        return serieConverter.toSerieResponse(serie);
    }

    public List<SerieResponse> findByCategory(final Long idCategory) {
        final List<SerieEntity> seriesEntity = serieRepository.findByCategoriesId(idCategory);

        return seriesEntity.stream().map(serie -> serieConverter.toSerieResponse(serie))
            .collect(Collectors.toList());
    }

    public List<SerieResponse> findByLabel(final String query) {
        String[] queries = query.split(" ");
        List<SerieResponse> serieResponses = new ArrayList<>();

        Arrays.stream(queries)
            .forEach(label -> serieResponses.addAll(findByLabelsLabelContaining(label)));

        return serieResponses.stream()
            .collect(collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(comparingLong(SerieResponse::getId))), ArrayList::new));
    }

    public SerieWatchedResponse watch(final SerieWatchedRequest request) {
        final SerieWatchedEntity serieWatchedEntity = serieConverter.toSerieWatchedEntity(request);
        final SerieResponse serie = findById(serieWatchedEntity.getId().getSerie().getId());
        final SerieWatchedEntity serieWatched = serieWatchedRepository.save(serieWatchedEntity);

        return SerieWatchedResponse.builder()
            .idUser(request.getIdUser())
            .series(singletonList(
                SerieWatchResponse.builder()
                    .serie(serie)
                    .date(serieWatched.getDate())
                    .build()))
            .build();
    }

    public SerieWatchedResponse watched(final Long idUser) {
        List<SerieWatchedEntity> serieWatchedEntities = serieWatchedRepository.findByIdIdUser(idUser);
        return serieConverter.toSerieWatchedResponse(serieWatchedEntities);
    }

    public List<SerieWatchedByCategoryResponse> topSerieWatchedByCategory() {
        return serieRepository.topSerieWatchedByCategory();
    }

    public void favoriteSerie(final SerieFavoriteRequest serieFavoriteRequest) {
        final FavoriteEntity favoriteEntity = favoriteConverter.serieToFavoriteEntity(serieFavoriteRequest);
        findById(favoriteEntity.getIdSerie());
        favoriteRepository.save(favoriteEntity);
    }

    public List<SerieResponse> favorites(final Long idUser) {
        final Optional<List<FavoriteEntity>> favorites = favoriteRepository.findByIdUser(idUser);

        return favorites.orElse(new ArrayList<>())
            .stream()
            .filter(favorite -> favorite.getIdSerie() != null)
            .map(favorite -> findById(favorite.getIdSerie()))
            .collect(Collectors.toList());
    }

    public void like(final SerieLikeRequest serieLikeRequest) {
        final LikeEntity likeEntity = likeConverter.serieToLikeEntity(serieLikeRequest);
        findById(likeEntity.getIdSerie());
        likeRepository.save(likeEntity);
    }

    public List<SerieResponse> likes(final Long idUser) {
        final Optional<List<LikeEntity>> likes = likeRepository.findByIdUser(idUser);

        return likes.orElse(new ArrayList<>())
            .stream()
            .filter(like -> like.getIdSerie() != null)
            .map(like -> findById(like.getIdSerie()))
            .collect(Collectors.toList());
    }

}
