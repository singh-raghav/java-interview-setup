package com.real.interview.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="movies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="movie_title", length=50)
    private String title;

    @Column(name="movie_release_year", length=10)
    private String releaseYear;

    @Column(name="creation_ts")
    private LocalDateTime creationTS;

    @Column(name="last_update_ts")
    private LocalDateTime lastUpdateTS;

    @PrePersist
    protected void onCreate(){
        this.creationTS=LocalDateTime.now();
        this.lastUpdateTS=LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.lastUpdateTS=LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", creationTS=" + creationTS +
                ", lastUpdateTS=" + lastUpdateTS +
                '}';
    }
}
