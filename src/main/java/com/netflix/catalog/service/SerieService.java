package com.netflix.catalog.service;

import com.netflix.catalog.converter.SerieConverter;
import com.netflix.catalog.dto.SerieRequest;
import com.netflix.catalog.dto.SerieResponse;
import com.netflix.catalog.entity.SerieEntity;
import com.netflix.catalog.exception.CatalogException;
import com.netflix.catalog.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    private void verifySerieName(final String name) {
        serieRepository.findByName(name)
            .ifPresent(serie -> {
                final String message = "Serie %s already exists";
                throw new CatalogException(HttpStatus.NOT_FOUND, String.format(message, name));
            });
    }

    private List<SerieResponse> findByLabelsLabelContaining(final String label) {
        final List<SerieEntity> moviesEntity = serieRepository.findByLabelsLabelContaining(label);

        return moviesEntity.stream().map(serieConverter::toSerieResponse)
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

}
