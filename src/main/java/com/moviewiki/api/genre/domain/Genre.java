package com.moviewiki.api.genre.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name="GENRES")
public class Genre {
    @Id
    private @Column(name = "GENRE_ID") String genreId;

    private @Column(name = "GENRE_NAME") String genreName;

}
