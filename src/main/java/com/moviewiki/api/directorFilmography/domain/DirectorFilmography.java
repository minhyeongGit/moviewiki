package com.moviewiki.api.directorFilmography.domain;

import com.moviewiki.api.director.domain.Director;
import com.moviewiki.api.movie.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DIRECTOR_FILMOGRAPHY")
@IdClass(DirectorFilmographyPK.class)
public class DirectorFilmography {

	@Id
	@ManyToOne(targetEntity = Director.class)
	@JoinColumn(name = "DIRECTOR_ID")
	private Director director;

	@Id
	@ManyToOne(targetEntity = Movie.class)
	@JoinColumn(name = "MOVIE_ID")
	private Movie movie;

}
