package com.netflix.catalog.service;

import com.netflix.catalog.converter.SerieConverter;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.dto.SerieRequest;
import com.netflix.catalog.dto.SerieResponse;
import com.netflix.catalog.entity.MovieEntity;
import com.netflix.catalog.entity.SerieEntity;
import com.netflix.catalog.exception.CatalogException;
import com.netflix.catalog.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SerieConverter serieConverter;

    @Autowired
    private CategoryService categoryService;

    private void verifySerieName(final String name) {
        serieRepository.findByName(name)
            .ifPresent(serie -> {
                final String message = "Serie %s already exists";
                throw new CatalogException(HttpStatus.NOT_FOUND, String.format(message, name));
            });
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

    public Page<SerieResponse> findByCategory(final Long idCategory, final Pageable pageable) {
        final List<SerieEntity> seriesEntity = serieRepository.findByCategoriesId(idCategory, pageable);

        List<SerieResponse> series = seriesEntity.stream().map(serie -> serieConverter.toSerieResponse(serie))
            .collect(Collectors.toList());

        return new PageImpl<>(series);
    }

}
