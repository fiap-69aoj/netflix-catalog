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

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SerieConverter serieConverter;

    public SerieResponse save(final SerieRequest request) {
        final SerieEntity serie = serieRepository.save(serieConverter.toSerieEntity(request));
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

}
