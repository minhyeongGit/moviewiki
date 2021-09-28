package com.moviewiki.api.prefNation.domain;

import com.moviewiki.api.nation.domain.Nation;
import com.moviewiki.api.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PrefNationPK implements Serializable {

    private User user;
    private Nation nation;

}
