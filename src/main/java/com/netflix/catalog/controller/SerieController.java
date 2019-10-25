package com.netflix.catalog.controller;

import com.netflix.catalog.dto.SerieRequest;
import com.netflix.catalog.dto.SerieResponse;
import com.netflix.catalog.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/serie")
public class SerieController {

    @Autowired
    private SerieService serieService;

    public ResponseEntity<SerieResponse> save(@Valid @RequestBody final SerieRequest request) {
        return null;
    }

}
