package com.moviewiki.api.actorFilmography.service;

import com.moviewiki.api.actor.domain.Actor;
import com.moviewiki.api.actorFilmography.domain.ActorFilmography;
import com.moviewiki.api.actorFilmography.repository.ActorFilmographyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorFilmographyServiceImpl implements ActorFilmographyService {

    @Autowired
    private ActorFilmographyRepository actorFilmographyRepository;

    @Override
    public List<ActorFilmography> findActorFilmographyListByActor(Actor actor){
        return actorFilmographyRepository.findActorFilmographyListByActor(actor);
    }
}
