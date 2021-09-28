package com.moviewiki.api.prefGenre.domain;

import com.moviewiki.api.genre.domain.Genre;
import com.moviewiki.api.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PrefGenrePK implements Serializable {

    private User user;
    private Genre genre;

}
