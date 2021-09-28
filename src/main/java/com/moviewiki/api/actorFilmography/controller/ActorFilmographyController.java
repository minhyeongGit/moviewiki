package com.moviewiki.api.actorFilmography.controller;

import com.moviewiki.api.actorFilmography.domain.ActorFilmography;
import com.moviewiki.api.actorFilmography.service.ActorFilmographyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActorFilmographyController {
    @Autowired
    private ActorFilmographyServiceImpl actorFilmographyServiceImpl;

    @GetMapping("/actorfilmography/read/actor")
    public String readActor(){
        return "readActor";
    }

    @PostMapping("/actorfilmography/read/actor")
    public String readActorId(ActorFilmography actorFilmography, Model model) {
        List<ActorFilmography> actorList2 = actorFilmographyServiceImpl.findActorFilmographyListByActor(actorFilmography.getActor());
        model.addAttribute("actors2",actorList2);
        return "readActor";
    }

}
