package com.moviewiki.api.weather.domain;

import com.moviewiki.api.movie.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(WeatherPK.class)
@Table(name="weathers")
public class Weather {

    @Id
    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;

    @Id
    private @Column(name = "weather_name") String weatherName;

    private @Column(name = "weather_point") int weatherPoint;
}
