package com.moviewiki.api.directorFilmography.controller;

import com.moviewiki.api.directorFilmography.domain.DirectorFilmography;
import com.moviewiki.api.directorFilmography.service.DirectorFilmographyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DirectorFilmographyController {
    @Autowired
    private DirectorFilmographyServiceImpl directFilmographyServiceImpl;

    @GetMapping("/directorfilmography/read/director")
    public String readDirector(){
        return "readDirector";
    }

    @PostMapping("/directorfilmography/read/director")
    public String readDirectorId(DirectorFilmography directorfilmography, Model model) {
        List<DirectorFilmography> directorList2 = directFilmographyServiceImpl.findDirectorFilmographyListByDirector(directorfilmography.getDirector());
        model.addAttribute("directors2",directorList2);
        return "readDirector";
    }

}

