package com.moviewiki.api.movieNation.controller;

import com.moviewiki.api.movieNation.domain.MovieNation;
import com.moviewiki.api.movieNation.service.MovieNationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieNationController {
    @Autowired
    private MovieNationServiceImpl movieNationServiceimpl;


    @GetMapping("/movieNation/read/nation")
    public String readNation(){
        return "readMovieNation";
    }

    @PostMapping("/movieNation/read/nation")
    public String readNationId(MovieNation movieNation, Model model) {
        List<MovieNation> nationrList2 = movieNationServiceimpl.findMovieNationListByNation(movieNation.getNation());
        model.addAttribute("nations2",nationrList2);
        return "readMovieNation";
    }
}
