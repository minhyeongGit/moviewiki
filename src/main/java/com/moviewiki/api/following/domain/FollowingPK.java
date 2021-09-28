package com.moviewiki.api.following.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import lombok.Data;
import com.moviewiki.api.user.domain.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowingPK implements Serializable {

    private User follower;
    private User followee;
}
