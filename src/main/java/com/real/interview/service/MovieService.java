package com.real.interview.service;

import com.real.interview.dto.MovieDto;

import java.util.List;

public interface MovieService {

    MovieDto createMovie(MovieDto movieDto);

    MovieDto findMovieById(Long id);

    MovieDto updateMovie(Long id, MovieDto movieDto);

    boolean deleteMovie(Long id);

    List<MovieDto> searchMovies(String title, String releaseYear);
}
