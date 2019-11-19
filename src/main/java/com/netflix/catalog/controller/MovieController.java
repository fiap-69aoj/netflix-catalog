package com.netflix.catalog.controller;

import com.netflix.catalog.dto.MovieFavoriteRequest;
import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.dto.MovieWatchedByCategoryResponse;
import com.netflix.catalog.dto.MovieWatchedRequest;
import com.netflix.catalog.dto.MovieWatchedResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
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
        final MovieResponse movie = movieService.save(request);
        return ResponseEntity.created(URI.create("/movies/" + movie.getId())).build();
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
    public List<MovieResponse> findByCategory(@PathVariable final Long idCategory) {
        return movieService.findByCategory(idCategory);
    }

    @GetMapping("/search")
    public List<MovieResponse> findByLabel(@RequestParam(value = "q") final String query) {
        return movieService.findByLabel(query);
    }

    @PostMapping("/watch")
    public ResponseEntity<MovieWatchedResponse> watch(@Valid @RequestBody final MovieWatchedRequest request) {
        final MovieWatchedResponse movieWatched = movieService.watch(request);
        return ResponseEntity.created(URI.create("/user/" + movieWatched.getIdUser() + "/watched")).build();
    }

    @GetMapping("/user/{idUser}/watched")
    public MovieWatchedResponse watched(@PathVariable final Long idUser) {
        return movieService.watched(idUser);
    }

    @GetMapping("/top/category")
    public List<MovieWatchedByCategoryResponse> topMovieWatchedByCategory() {
        return movieService.topMovieWatchedByCategory();
    }

    @PostMapping("/favorites")
    public ResponseEntity sendToFavorites(@Valid @RequestBody final MovieFavoriteRequest movieFavoriteRequest) {
        movieService.sendToFavorites(movieFavoriteRequest);
        return ResponseEntity.created(URI.create("/favorites")).build();
    }

    @GetMapping("/user/{idUser}/favorites")
    public ResponseEntity<List<MovieResponse>> favorites(@PathVariable final Long idUser) {
        return ResponseEntity.ok(movieService.favorites(idUser));
    }

}
