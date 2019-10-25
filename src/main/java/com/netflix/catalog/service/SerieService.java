package com.netflix.catalog.service;

import com.netflix.catalog.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

}
