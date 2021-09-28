package com.moviewiki.api.movieGenre.domain;

import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.movie.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MOVIE_GENRES")
@IdClass(MovieGenrePK.class)
public class MovieGenre {

	@Id
	@ManyToOne(targetEntity = Genre.class)
	@JoinColumn(name = "GENRE_ID")
	private Genre genre;

	@Id
	@ManyToOne(targetEntity = Movie.class)
	@JoinColumn(name = "MOVIE_ID")
	private Movie movie;
}
