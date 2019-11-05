package com.netflix.catalog.service;

import com.netflix.catalog.converter.MovieConverter;
import com.netflix.catalog.dto.CategoryRequest;
import com.netflix.catalog.dto.MovieRequest;
import com.netflix.catalog.dto.MovieResponse;
import com.netflix.catalog.entity.CategoryEntity;
import com.netflix.catalog.entity.MovieEntity;
import com.netflix.catalog.repository.CategoryRepository;
import com.netflix.catalog.repository.MovieRepository;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    // @Mock
    // private MovieRepository movieRepository;

    private MovieConverter movieConverter = new MovieConverter();

    // @Mock
    // private CategoryRepository categoryRepository;

    // @Mock
    // private CategoryService categoryService;

    @Test
    public void givenValidMovie_whenSaveMovie_thenReturnMovieResponse() {
        // Given
        MovieRequest movieRequest = MovieRequest.builder()
            .name("teste6")
            .rating("L")
            .summary("summary")
            .releaseDate(new Date(Calendar.DATE))
            .labels(Arrays.asList("label1"))
            .categories(Arrays.asList(
                CategoryRequest.builder()
                    .id(1L)
                    .build()
            ))
            .build();

        MovieEntity movieEntity = MovieEntity.builder()
            .categories(Arrays.asList(
                CategoryEntity.builder()
                    .id(1L)
                    .build()
            ))
            .build();

        MovieResponse movieResponse = MovieResponse.builder()
            .id(1L)
            .build();

        // when(movieConverter.toMovieEntity(any(MovieRequest.class))).thenReturn(movieEntity);
        // when(movieRepository.save(any(MovieEntity.class))).thenReturn(movieEntity);
        // when(movieConverter.toMovieResponse(any(MovieEntity.class))).thenReturn(movieResponse);

        // MovieRepository movieRepository = mock(MovieRepository.class);
        // MovieConverter movieConverter = new MovieConverter();
        // CategoryService categoryService = new CategoryService();
        // MovieService movieService = new MovieService(movieRepository, movieConverter, categoryService);
        // When
        MovieResponse movie = movieService.save(movieRequest);

        // Then
        assertNotNull(movie);
        assertEquals(1L, movie.getId());
    }

}