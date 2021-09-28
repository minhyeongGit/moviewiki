package com.moviewiki.api.prefDirector.domain;

import com.moviewiki.api.director.domain.Director;
import com.moviewiki.api.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PrefDirectorPK implements Serializable {

    private User user;
    private Director director;

}
