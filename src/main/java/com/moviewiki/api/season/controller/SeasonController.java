package com.moviewiki.api.season.controller;

import com.moviewiki.api.movie.service.MovieServiceImpl;
import com.moviewiki.api.season.domain.Season;
import com.moviewiki.api.season.repository.SeasonRepository;
import com.moviewiki.api.season.service.SeasonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SeasonController {

    @Autowired
    SeasonRepository seasonServiceRepository;

    @Autowired
    MovieServiceImpl movieServiceImpl;

    // 특정 영화의 계절점수 조회
    @GetMapping("/season/read")
    @ResponseBody
    public List<Season> readSeason(){

        String seasonName;
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();

        // 3·4·5월을 봄, 6·7·8월을 여름, 9·10·11월을 가을, 12·1·2월을 겨울
        switch(month){
            case 3: case 4: case 5:
                seasonName = "봄";
                break;
            case 6: case 7: case 8:
                seasonName = "여름";
                break;
            case 9: case 10: case 11:
                seasonName = "가을";
                break;
            default:
                seasonName = "겨울";
                break;
        }

        return (seasonServiceRepository.findBySeasonNameOrderBySeasonPointDesc(seasonName)).subList(0, 12);
    }

}
