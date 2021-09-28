package com.moviewiki.api.actor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
		name = "ACTOR_SEQ_GENERATOR",
		sequenceName = "ACTOR_SEQ",
		initialValue = 1,
		allocationSize = 1)
@Table(name="ACTORS")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "ACTOR_SEQ_GENERATOR")
	@Column(name = "ACTOR_ID")
	private Long actorId;

	private @Column(name = "ACTOR_NAME") String actorName;
	private @Column(name = "ACTOR_PROFILE") String actorProfile;

}

