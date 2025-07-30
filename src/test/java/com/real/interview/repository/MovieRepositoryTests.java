package com.real.interview.repository;

import com.real.interview.entity.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

}
