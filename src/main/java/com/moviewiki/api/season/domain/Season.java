package com.moviewiki.api.season.domain;

import com.moviewiki.api.movie.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SeasonPK.class)
@Table(name="SEASONS")
public class Season {

    @Id
    @ManyToOne
    @JoinColumn(name="MOVIE_ID")
    private Movie movie;

    @Id
    private @Column(name = "SEASON_NAME") String seasonName;

    private @Column(name = "SEASON_POINT") int seasonPoint;
}
