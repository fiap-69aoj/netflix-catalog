package com.netflix.catalog.controller;

import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody final MovieRequest request) {
        final MovieResponse response = movieService.save(request);
        return ResponseEntity.created(URI.create("/movie/" + response.getId())).build();
    }

}
