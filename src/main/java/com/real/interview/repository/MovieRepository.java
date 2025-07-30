package com.real.interview.repository;

import com.real.interview.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT me FROM Movie me WHERE me.title = :title and me.releaseYear=:releaseYear")
    Collection<Movie> searchMoviesByTitleAndReleaseYear(String title, String releaseYear);
}
