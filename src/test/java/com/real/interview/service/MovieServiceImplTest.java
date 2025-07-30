package com.real.interview.service;

import com.real.interview.dto.MovieDto;
import com.real.interview.entity.Movie;
import com.real.interview.exception.MovieNotFoundException;
import com.real.interview.repository.MovieRepository;
import com.real.interview.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    public void testSaveMovie_HappyPath(){

        // Arrange
        MovieDto inputMovieDto = new MovieDto();
        inputMovieDto.setTitle("Inception");
        inputMovieDto.setReleaseYear("2010");

        Movie savedEntity = new Movie();
        savedEntity.setId(1L);
        savedEntity.setTitle("Inception");
        savedEntity.setReleaseYear("2010");

        MovieDto expectedMovieDto = new MovieDto();
        expectedMovieDto.setId(1L);
        expectedMovieDto.setTitle("Inception");
        expectedMovieDto.setReleaseYear("2010");

        when(movieRepository.save(any(Movie.class))).thenReturn(savedEntity);

        // Act
        MovieDto result = movieService.createMovie(inputMovieDto);

        // Assert
        assertNotNull(result);
        assertEquals(expectedMovieDto.getId(), result.getId());
        assertEquals(expectedMovieDto.getTitle(), result.getTitle());
        assertEquals(expectedMovieDto.getReleaseYear(), result.getReleaseYear());
        verify(movieRepository).save(any(Movie.class));

    }

    @Test
    public void tesFindMovieById_HappyPath(){

        // Arrange
        Long movieId = 1L;
        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setTitle("Inception");
        movie.setReleaseYear("2010");

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        MovieDto expectedMovieDto = new MovieDto();
        expectedMovieDto.setId(movieId);
        expectedMovieDto.setTitle("Inception");
        expectedMovieDto.setReleaseYear("2010");

        // Act
        MovieDto result = movieService.findMovieById(movieId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedMovieDto.getId(), result.getId());
        assertEquals(expectedMovieDto.getTitle(), result.getTitle());
        assertEquals(expectedMovieDto.getReleaseYear(), result.getReleaseYear());
        verify(movieRepository).findById(movieId);

    }

    @Test
    void findMovieById_shouldThrowException_whenMovieDoesNotExist() {
        // Arrange
        Long movieId = 1L;
        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(MovieNotFoundException.class, () -> movieService.findMovieById(movieId));
        verify(movieRepository).findById(movieId);
    }

}
