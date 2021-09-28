package com.moviewiki.api.actor.controller;

import com.moviewiki.api.actor.domain.Actor;
import com.moviewiki.api.actor.service.ActorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ActorController {

    @Autowired
    private ActorServiceImpl actorServiceImpl;


    /* 배우 등록 페이지 이동*/
    @GetMapping("/movie/create/actor")
    public String actorForm(){
        return "admin/popup_add_actor";
    }


    /* 배우 정보 저장 */
    @PostMapping("/movie/create/actor")
    public String createActor(Actor actor){
        actorServiceImpl.save(actor);
        return "admin/popup_dc_actor";
    }

    /* 배우 목록 조회 페이지 이동 */
    @GetMapping("/movie/read/actor")
    public String readActor(){
        return "admin/popup_dc_actor";
    }


    /* 특정 이름의 배우 목록 조회 */
    @PostMapping("/movie/read/actor")
    public String readActor(Actor actor, Model model){
        List<Actor> actorList = actorServiceImpl.findByActorName(actor.getActorName());
        model.addAttribute("actors", actorList);
        return "admin/popup_dc_actor";
    }

    // 배우 상세 페이지 조회
    @GetMapping("/movie/actor/{actorId}")
    public String movieActor(@PathVariable Long actorId, Model model) {

        model.addAttribute("actor", actorServiceImpl.findByActorId(actorId));
        return "member_template/actorPage";
    }
}