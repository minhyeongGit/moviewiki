package com.moviewiki.api.weather.service;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.weather.domain.Weather;

import java.util.List;

public interface WeatherService {

    // 영화의 날씨점수 등록 및 업데이트
    void save(Movie movie,String weatherName, int weatherPoint);

    // 특정날씨의 점수에 따른 정렬
    List<Weather> findByWeatherNameOrderByWeatherPointDesc(String weatherName);

    // 오늘 날씨 받기
    String todayWeather();
}
