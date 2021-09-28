package com.moviewiki.api.weather.controller;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.movie.service.MovieServiceImpl;
import com.moviewiki.api.weather.domain.Weather;
import com.moviewiki.api.weather.repository.WeatherRepository;
import com.moviewiki.api.weather.service.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Controller
public class WeatherController {

    @Autowired
    WeatherServiceImpl weatherServiceImpl;
    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    MovieServiceImpl movieServiceImpl;

    // 날씨점수 등록 페이지 이동
    @GetMapping("/weather/create")
    public String createWeather(){
        return "member/weather_add";
    }

    // 영화에 대한 날씨점수 등록
    @PostMapping("/weather/create")
    @ResponseBody
    public void createSeason(HttpServletRequest request){
        Long movieId = parseLong(request.getParameter("movieId"));
        String weatherName = request.getParameter("weatherName");
        int weatherPoint = parseInt(request.getParameter("weatherPoint"));

        Movie movie = movieServiceImpl.findByMovieId(movieId);

        weatherServiceImpl.save(movie, weatherName, weatherPoint);

    }



    //  특정 영화의 날씨점수 조회
    @GetMapping("/weather/read")
    @ResponseBody
    public List<Weather> readWeather(){

        String weatherName ="";

        String weather = weatherServiceImpl.todayWeather();
        if (weather == "Rain"){
            weatherName = "비";
        } else if (weather == "Snow"){
            weatherName = "눈";
        }
        return weatherRepository.findByWeatherNameOrderByWeatherPointDesc(weatherName);
    }
}
