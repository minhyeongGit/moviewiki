package com.moviewiki.api.weather.domain;

import com.moviewiki.api.movie.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherPK implements Serializable {

    private Movie movie;
    private String weatherName;
}
