package com.moviewiki.api.movieNation.domain;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.nation.domain.Nation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MOVIE_NATIONS")
@IdClass(MovieNationPK.class)
public class MovieNation {

		@Id
		@ManyToOne(targetEntity = Nation.class)
		@JoinColumn(name = "NATION_ID")
		private Nation nation;

		@Id
		@ManyToOne(targetEntity = Movie.class)
		@JoinColumn(name = "MOVIE_ID")
		private Movie movie;

}
