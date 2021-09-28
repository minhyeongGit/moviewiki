package com.moviewiki.api.movie.controller;

import com.moviewiki.api.actor.domain.Actor;
import com.moviewiki.api.movie.domain.Movie;
import com.moviewiki.api.movie.domain.MovieForm;
import com.moviewiki.api.movie.service.MovieServiceImpl;
import com.moviewiki.api.user.domain.User;
import com.moviewiki.api.user.service.UserManagementServiceImpl;
import com.moviewiki.api.wantToSee.service.WantToSeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Controller
public class MovieController {

    @Autowired
    private MovieServiceImpl movieServiceImpl;
    @Autowired
    private UserManagementServiceImpl userManagementService;
    @Autowired
    private WantToSeeServiceImpl wantToSeeServiceImpl;

    /* 영화 상세 페이지 이동(회원용) */
    @GetMapping("/main/movie/{movieId}")
    public String movieDetail_member(@PathVariable Long movieId, Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser) {
        User user = userManagementService.getUser(currentUser.getUsername());
        Movie movie = movieServiceImpl.findByMovieId(movieId);

        model.addAttribute("isWant", wantToSeeServiceImpl.isWant(user, movie));
        model.addAttribute("currentUserId", currentUser.getUsername());
        model.addAttribute("movie", movieServiceImpl.findByMovieId(movieId));

        return "member_template/moviesingle";
    }

    /* 영화 상세 페이지 이동(비회원용) */
    @GetMapping("/main/nonMember_movie/{movieId}")
    public String movieDetail_nonMember(@PathVariable Long movieId, Model model) {
        Movie movie = movieServiceImpl.findByMovieId(movieId);
        model.addAttribute("movie", movieServiceImpl.findByMovieId(movieId));

        return "member_template/moviesingle";
    }

    /* 영화 등록 페이지 이동 */
    @GetMapping("/movie/create")
    public String movieForm(){
        return "admin/admin_movie_add";
    }

    /* 영화 정보 저장 */
    @PostMapping("/movie/create")
    public String createMovie(MovieForm form){
       Movie movie = new Movie(form.getFilmRating(), form.getMovieName(), form.getMovieOgName(), form.getMovieProfile(), Date.valueOf(form.getReleaseDate())
                                , parseInt(form.getRunningTime()), form.getSummary(), 0, 0, 0);

       movieServiceImpl.save(movie);
        return "admin/admin_movie_add";
    }

    /* 특정 영화 조회 */
    @GetMapping("/movie/read/{movieId}")
    @ResponseBody
    public Optional<Movie> readMovie(@PathVariable(name = "movieId") Long movieId){
        Optional<Movie> movie = movieServiceImpl.findById(movieId);
        return movie;
    }


    /* 모든 영화 조회 */
    @GetMapping("/movie/read")
    @ResponseBody
    public List<Movie> readAllMovie(Actor actor){
        List<Movie> movieList = movieServiceImpl.findAll();
        return movieList;
    }

    // 입력받는 검색 test
    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<MovieForm> searchList = movieServiceImpl.searchMovies(keyword);
        model.addAttribute("searchList", searchList);
        return "search";
        //http://localhost:8081/search?keyword로 들어가야됨
    }

}