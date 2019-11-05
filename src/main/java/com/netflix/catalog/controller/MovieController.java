package com.netflix.catalog.controller;

import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody final MovieRequest request) {
        final MovieResponse response = movieService.save(request);
        return ResponseEntity.created(URI.create("/movies/" + response.getId())).build();
    }

    @GetMapping
    public Page<MovieResponse> findAll(final Pageable pageable) {
        return movieService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable final Long id) {
        final MovieResponse movie = movieService.findById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/category/{idCategory}")
    public Page<MovieResponse> findByCategory(@PathVariable final Long idCategory, Pageable pageable) {
        return movieService.findByCategory(idCategory, pageable);
    }

}
