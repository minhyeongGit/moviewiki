package com.moviewiki.api.weather.service;

import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.weather.domain.Weather;
import com.moviewiki.api.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    EntityManager em;

    /* 영화의 날씨점수 등록 및 업데이트 */
    @Override
    @Transactional
    public void save(Movie movie, String weatherName, int weatherPoint) {

        Weather weather = new Weather();
        weather.setMovie(movie);
        weather.setWeatherName(weatherName);
        weather.setWeatherPoint(weatherPoint);

        em.persist(weather);
    }

    /* 특정날씨의 점수에 따른 정렬 */
    @Override
    public List<Weather> findByWeatherNameOrderByWeatherPointDesc(String weatherName) {
        return weatherRepository.findByWeatherNameOrderByWeatherPointDesc(weatherName);
    }

    // 오늘 날씨 받기
    @Override
    public String todayWeather(){
        // 파싱한 데이터를 저장할 변수
        String result = "";

        try {

            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=3c9eb9ffb7fe11436738555f2ccaea2a");

            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject weather = (JSONObject)jsonObject.get("weather");

            System.out.println("weather = " + weather.get("main").toString());
            return weather.get("main").toString();

        }catch(Exception e) {
           e.printStackTrace();
            return "error";
        }
    }
}
