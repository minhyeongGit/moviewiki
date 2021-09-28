package com.moviewiki.api.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name="USERS")
public class User {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_PW")
    private String userPw;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_BIRTH", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyMMdd")
    private Date userBirth;

    @Column(name = "USER_GENDER", columnDefinition = "number")
    private int userGender;

    @Column(name = "USER_MAIL")
    private String userMail;

    @Column(name = "USER_CELLNUM")
    private String userCellNum;

    @Column(name = "USER_MBTI")
    private String userMbti;

    @Column(name = "USER_ADMIN", columnDefinition = "char(1)")
    private boolean userAdmin;

}
