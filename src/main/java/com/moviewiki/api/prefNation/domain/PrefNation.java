package com.moviewiki.api.prefNation.domain;

import com.moviewiki.api.nation.domain.Nation;
import com.moviewiki.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PREF_NATIONS")
@IdClass(PrefNationPK.class)
public class PrefNation {

    @ManyToOne
    @JoinColumn(name ="USER_ID")
    @Id
    private User user;

    @ManyToOne
    @JoinColumn(name ="NATION_ID")
    @Id
    private Nation nation;

    @Column(name = "NATION_POINT")
    private double nationPoint;

    @Column(name = "NATION_REVIEW_COUNT")
    private int nationReviewCount;

    @Column(name = "NATION_REVIEW_DATE")
    private Date nationReviewDate;

}
