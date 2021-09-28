package com.moviewiki.api.weather.repository;

import com.moviewiki.api.weather.domain.Weather;
import com.moviewiki.api.weather.domain.WeatherPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, WeatherPK> {

    // 특정날씨의 점수에 따른 정렬
    List<Weather> findByWeatherNameOrderByWeatherPointDesc(String weatherName);
}
