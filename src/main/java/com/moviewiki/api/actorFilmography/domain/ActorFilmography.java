package com.moviewiki.api.actorFilmography.domain;

import com.moviewiki.api.actor.domain.Actor;
import com.moviewiki.api.like.domain.LikePK;
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
@Table(name = "ACTOR_FILMOGRAPHY")
@IdClass(ActorFilmographyPK.class)
public class ActorFilmography {

	@Id
	@ManyToOne(targetEntity = Actor.class)
	@JoinColumn(name = "ACTOR_ID")
	private Actor actor;

	@Id
	@ManyToOne(targetEntity = Movie.class)
	@JoinColumn(name = "MOVIE_ID")
	private Movie movie;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "CHARACTERS")
	private String characters;

}