package com.real.interview.controller;

import com.real.interview.dto.MovieDto;
import com.real.interview.service.MovieService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }

    @PostMapping("/save")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto){

        if(ObjectUtils.isEmpty(movieDto))
            return ResponseEntity.badRequest().build();
        MovieDto createdMovieDto = movieService.createMovie(movieDto);
        return ResponseEntity.ok(createdMovieDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findMovieById(@PathVariable Long id){

        if(id==null)
            return ResponseEntity.badRequest().build();
        MovieDto movieDto = movieService.findMovieById(id);
        return ResponseEntity.ok(movieDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto){

        if(id==null || ObjectUtils.isEmpty(movieDto))
            return ResponseEntity.badRequest().build();
        MovieDto updatedMovieDto = movieService.updateMovie(id, movieDto);
        return ResponseEntity.ok(updatedMovieDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDto> deleteMovie(@PathVariable Long id){

        if(id==null)
            return ResponseEntity.badRequest().build();
        boolean isDeleted = movieService.deleteMovie(id);
        if(isDeleted)
            return ResponseEntity.ok(new MovieDto());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> searchMovies(@RequestParam String title, @RequestParam String releaseYear){

        if(StringUtils.isBlank(title) || StringUtils.isBlank(releaseYear))
            ResponseEntity.badRequest().build();
        List<MovieDto> movieDtos = movieService.searchMovies(title,releaseYear);
        return ResponseEntity.ok(movieDtos);

    }

}
