package com.netflix.catalog.controller;

import com.netflix.catalog.dto.SerieRequest;
import com.netflix.catalog.dto.SerieResponse;
import com.netflix.catalog.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @PostMapping
    public ResponseEntity<SerieResponse> save(@Valid @RequestBody final SerieRequest request) {
        final SerieResponse response = serieService.save(request);
        return ResponseEntity.created(URI.create("/series/" + response.getId())).build();
    }

    @GetMapping
    public Page<SerieResponse> findAll(final Pageable pageable) {
        return serieService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieResponse> findById(@PathVariable final Long id) {
        final SerieResponse response = serieService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{idCategory}")
    public List<SerieResponse> findByCategory(@PathVariable final Long idCategory) {
        return serieService.findByCategory(idCategory);
    }

    @GetMapping("/search")
    public List<SerieResponse> findByLabel(@RequestParam(value = "q") String query) {
        return serieService.findByLabel(query);
    }

}
