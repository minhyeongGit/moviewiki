package com.moviewiki.api.user.controller;

import com.moviewiki.api.movie.service.MovieServiceImpl;
import com.moviewiki.api.prefActor.Service.PrefActorServiceImpl;
import com.moviewiki.api.prefDirector.service.PrefDirectorServiceImpl;
import com.moviewiki.api.prefGenre.service.PrefGenreServiceImpl;
import com.moviewiki.api.prefNation.Service.PrefNationServiceImpl;
import com.moviewiki.api.season.controller.SeasonController;
import com.moviewiki.api.user.domain.User;
import com.moviewiki.api.user.service.UserManagementService;
import com.moviewiki.api.weather.service.WeatherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UserManagementController {
    private static final Logger log = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserManagementService userManagementService;
    @Autowired
    private MovieServiceImpl movieServiceImpl;
    @Autowired
    private SeasonController seasonController;
    @Autowired
    private PrefGenreServiceImpl prefGenreServiceImpl;
    @Autowired
    private PrefNationServiceImpl prefNationServiceImpl;
    @Autowired
    private PrefActorServiceImpl prefActorServiceImpl;
    @Autowired
    private PrefDirectorServiceImpl prefDirectorServiceImpl;

    // ????????? ??? ???????????????
    @GetMapping("/")
    public String initMainPage(Model model) {
        model.addAttribute("movieDate", movieServiceImpl.findAllOrderByDate());
        model.addAttribute("movieReviewCount", movieServiceImpl.findAllOrderByReviewCount());
        model.addAttribute("movieRating", movieServiceImpl.findAllOrderByRating());

        return "member_template/main_before";
    }

    // ????????? ??? ???????????????
    @GetMapping("/main")
    public String MainPage(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser) {
        model.addAttribute("currentUserId", currentUser.getUsername());
        model.addAttribute("seasons", seasonController.readSeason());
        model.addAttribute("recGenreList", prefGenreServiceImpl.findAll(currentUser.getUsername()));
        model.addAttribute("recNationList", prefNationServiceImpl.findAll(currentUser.getUsername()));
        model.addAttribute("recActorList", prefActorServiceImpl.findAll(currentUser.getUsername()));
        model.addAttribute("recDirectorList", prefDirectorServiceImpl.findAll(currentUser.getUsername()));
        return "member_template/main";
    }

    // ????????? form call
    @RequestMapping("/login")
    public void loginPage() {
        log.info("/loginPage");
    }

    // ????????? ?????? ?????????
    @GetMapping("/loginFail")
    public void loginFail() {
    }

    // ????????? ????????? ????????? ?????????
    @RequestMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser, Map<String, Object> model,
                               SecurityContextHolderAwareRequestWrapper requestWrapper) {
        log.info("currentUser = " + currentUser);
        String nextPage = null;
        if (currentUser == null) {
            model.put("message", "???????????? ?????? ?????????");
            nextPage = "redirect:/denied";
        } else {
            if (requestWrapper.isUserInRole("ADMIN")) {
                nextPage = "redirect:/admin/admin_index";
            } else {
                model.put("currentUserId", currentUser.getUsername());
                nextPage = "redirect:/main";
            }
        }
        return nextPage;
    }

    // ????????? ????????? ??????
    @RequestMapping("/admin/admin_index")
    public void adminIndexPage() {
    }


    // ????????? ????????? ?????? ?????? ?????????
    @GetMapping("/denied")
    public String deniedPage() {
        return "denied";
    }

    // ??????????????? ?????? form call
    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("user", new User());
        log.info("/join");
        return "join";
    }

    // ???????????? -> ???????????? ????????? ??? DB insert
    @PostMapping("/createUser")
    public String createUser(User user) {
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        userManagementService.createUser(user);
        return "redirect:/login";
    }

    // ?????? ??????
    @RequestMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userManagementService.deleteUser(userId);
        return "redirect:/logout";
    }

    // ???????????? ?????? -> DB ??????
    @PostMapping("/updateUser/{userId}")
    public String updateUser(User user, @PathVariable String userId) {

        log.info("userId = " + user.getUserId());
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        userManagementService.updateUser(user);
        return "redirect:/member/mypage/" + userId;
    }

    // ???????????? ?????? form call
    @GetMapping("/member/modify_info")
    public String modifyInfoPage(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser) {
        model.addAttribute("currentUserId", currentUser.getUsername());
        model.addAttribute("currentUser", userManagementService.getUser(currentUser.getUsername()));
        return "/member/modify_info";
    }

    // ????????? ?????? form call
    @GetMapping("/find_id")
    public String findIdPage() {
        return "/find_id";
    }

    // ????????? ?????? DB ??????
    @PostMapping("/find_id")
    public String findId(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        String userMail = request.getParameter("userMail");
        User user = userManagementService.findId(userName, userMail);
        model.addAttribute("user", user);
        return "find_id_result";
    }

    // ????????? ?????? ?????? ?????????
    @RequestMapping("/find_id_result")
    public String findIdResult() {
        return "/login";
    }

    // ???????????? ?????? form call
    @GetMapping("/find_pw")
    public String findPwPage() {
        return "/find_pw";
    }

    // ???????????? ?????? DB ??????
    @PostMapping("/find_pw")
    public String findPw(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userMail = request.getParameter("userMail");
        User user = userManagementService.findPw(userId, userName, userMail);
        if(user == null) {
            System.out.println("???????????? ?????? ??????????????????.");
            return "redirect:/find_pw";
        }
        model.addAttribute("userId", userId);
        return "/member/change_pw";
    }

    // ???????????? ?????? ????????? form call
    @PostMapping("/change_pw")
    public String changePw(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String changePw = request.getParameter("userPw");
        User user = userManagementService.getUser(userId);

        user.setUserPw(passwordEncoder.encode(changePw));
        userManagementService.updateUser(user);

        return "/login";
    }

    // ???????????? ?????? ????????? form call
    @GetMapping("/member/check_pw")
    public String checkPwPage(Authentication auth, Model model) {
        String currentUserId = auth.getName();
        model.addAttribute("currentUserId", currentUserId);
        log.info("userId===========" + currentUserId);
        return "/member/check_pw";
    }

    // ???????????? ?????? -> DB ??????
    @PostMapping("/member/check_pw")
    public String checkPw(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        log.info("userId =========" + userId);
        log.info("userPw =========" + userPw);



        User DBUser = userManagementService.getUser(userId);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(userPw, DBUser.getUserPw())) {
            return "redirect:/member/modify_info";
        }
        System.out.println("??????????????? ???????????? ????????????."); // ??????????
        return "redirect:/member/check_pw";
    }


    // ?????? ????????? ????????? ????????? (???????????? ????????? ??????)
    @GetMapping("/dummy_pw")
    public String dummyPw() {
        List<User> userList = userManagementService.getAllUser();

        for(User user : userList) {
            String userPw = user.getUserPw();
            user.setUserPw(passwordEncoder.encode(userPw));
            userManagementService.updateUser(user);
        }
        return "redirect:/admin/admin_index";
    }
}