package com.real.interview.repository;

import com.real.interview.entity.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void MovieRepository_saveAll_ReturnSavedMovie(){

        //Arrange
        Movie movie = Movie.builder().title("F1").releaseYear("2025").build();

        //Act
        Movie savedMovie = movieRepository.save(movie);

        //Assert
        Assertions.assertThat(savedMovie).isNotNull();
    }

    @Test
    public void MovieRepository_findById_ReturnMovie(){

        //Arrange
        Movie movie = Movie.builder().title("F1").releaseYear("2025").build();
        Movie savedMovie = movieRepository.save(movie);

        //Act
        Optional<Movie> result = movieRepository.findById(movie.getId());

        //Assert
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getTitle()).isEqualTo(movie.getTitle());

    }

    @Test
    public void MovieRepository_deleteById_Success(){

        //Arrange
        Long movieId = 1L;
        Movie movie = Movie.builder().title("F1").releaseYear("2025").build();
        Movie savedMovie = movieRepository.save(movie);

        //Act
        movieRepository.deleteById(savedMovie.getId());

        //Assert
        Optional<Movie> result = movieRepository.findById(savedMovie.getId());

        Assertions.assertThat(result).isEmpty();

    }

    @Test
    public void MovieRepository_searchMoviesByTitleAndReleaseYear(){

        //Arrange
        Movie movie = Movie.builder().title("F1").releaseYear("2025").build();
        Movie savedMovie = movieRepository.save(movie);

        //Act
        Collection<Movie> movies = movieRepository.searchMoviesByTitleAndReleaseYear(movie.getTitle(),movie.getReleaseYear());

        //Assert
        Assertions.assertThat(movies).isNotNull();
        Assertions.assertThat(movies.size()).isEqualTo(1);
        List<Movie> movieList = new ArrayList<>(movies);
        Assertions.assertThat(movieList.get(0).getTitle()).isEqualTo(savedMovie.getTitle());

    }



}
