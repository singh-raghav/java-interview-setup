package com.real.interview.service.impl;

import com.real.interview.dto.MovieDto;
import com.real.interview.entity.Movie;
import com.real.interview.exception.MovieNotFoundException;
import com.real.interview.repository.MovieRepository;
import com.real.interview.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

    @Override
    @Transactional
    public MovieDto createMovie(MovieDto movieDto) {

        Movie movie = new Movie();
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setTitle(movieDto.getTitle());
        Movie createdMovie = movieRepository.save(movie);
        MovieDto savedMovieDto = new MovieDto();
        savedMovieDto.setId(createdMovie.getId());
        savedMovieDto.setTitle(createdMovie.getTitle());
        savedMovieDto.setReleaseYear(createdMovie.getReleaseYear());
        return savedMovieDto;
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDto findMovieById(Long id) {

        Optional<Movie> movieEntity = movieRepository.findById(id);
        if(movieEntity.isEmpty())
            throw new MovieNotFoundException("No movie found with id:"+id);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movieEntity.get().getId());
        movieDto.setTitle(movieEntity.get().getTitle());
        movieDto.setReleaseYear(movieEntity.get().getReleaseYear());
        return movieDto;
    }

    @Override
    @Transactional(rollbackFor = MovieNotFoundException.class)
    public MovieDto updateMovie(Long id, MovieDto movieDto) {

        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(()->new MovieNotFoundException("Movie not found with id:"+id));
        existingMovie.setTitle(movieDto.getTitle());
        existingMovie.setReleaseYear(movieDto.getReleaseYear());
        Movie updatedMovie = movieRepository.save(existingMovie);
        MovieDto updatedMovieDto = new MovieDto();
        updatedMovieDto.setId(updatedMovie.getId());
        updatedMovieDto.setTitle(updatedMovie.getTitle());
        updatedMovieDto.setReleaseYear(updatedMovie.getReleaseYear());
        return updatedMovieDto;
    }

    @Override
    @Transactional(rollbackFor = MovieNotFoundException.class)
    public boolean deleteMovie(Long id) {
        movieRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> searchMovies(String title, String releaseYear) {

        Collection<Movie> movieCollection = movieRepository.searchMoviesByTitleAndReleaseYear(title,releaseYear);
        if(CollectionUtils.isEmpty(movieCollection))
            return Collections.emptyList();
        List<MovieDto> movieDtos = new ArrayList<>();

        for(Movie entity : movieCollection){
            MovieDto movieDto = new MovieDto();
            movieDto.setId(entity.getId());
            movieDto.setTitle(entity.getTitle());
            movieDto.setReleaseYear(entity.getReleaseYear());
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }

}
