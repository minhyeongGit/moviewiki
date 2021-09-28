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

    // 로그인 전 메인페이지
    @GetMapping("/")
    public String initMainPage(Model model) {
        model.addAttribute("movieDate", movieServiceImpl.findAllOrderByDate());
        model.addAttribute("movieReviewCount", movieServiceImpl.findAllOrderByReviewCount());
        model.addAttribute("movieRating", movieServiceImpl.findAllOrderByRating());

        return "member_template/main_before";
    }

    // 로그인 후 메인페이지
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

    // 로그인 form call
    @RequestMapping("/login")
    public void loginPage() {
        log.info("/loginPage");
    }

    // 로그인 오류 메시지
    @GetMapping("/loginFail")
    public void loginFail() {
    }

    // 로그인 성공시 이동할 페이지
    @RequestMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser, Map<String, Object> model,
                               SecurityContextHolderAwareRequestWrapper requestWrapper) {
        log.info("currentUser = " + currentUser);
        String nextPage = null;
        if (currentUser == null) {
            model.put("message", "유효하지 않은 데이터");
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

    // 관리자 페이지 이동
    @RequestMapping("/admin/admin_index")
    public void adminIndexPage() {
    }


    // 비정상 접속시 접근 불가 페이지
    @GetMapping("/denied")
    public String deniedPage() {
        return "denied";
    }

    // 회원가입을 위한 form call
    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("user", new User());
        log.info("/join");
        return "join";
    }

    // 회원가입 -> 패스워드 암호화 및 DB insert
    @PostMapping("/createUser")
    public String createUser(User user) {
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        userManagementService.createUser(user);
        return "redirect:/login";
    }

    // 회원 탈퇴
    @RequestMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userManagementService.deleteUser(userId);
        return "redirect:/logout";
    }

    // 회원정보 수정 -> DB 저장
    @PostMapping("/updateUser/{userId}")
    public String updateUser(User user, @PathVariable String userId) {

        log.info("userId = " + user.getUserId());
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        userManagementService.updateUser(user);
        return "redirect:/member/mypage/" + userId;
    }

    // 회원정보 수정 form call
    @GetMapping("/member/modify_info")
    public String modifyInfoPage(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser) {
        model.addAttribute("currentUserId", currentUser.getUsername());
        model.addAttribute("currentUser", userManagementService.getUser(currentUser.getUsername()));
        return "/member/modify_info";
    }

    // 아이디 찾기 form call
    @GetMapping("/find_id")
    public String findIdPage() {
        return "/find_id";
    }

    // 아이디 찾기 DB 조회
    @PostMapping("/find_id")
    public String findId(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        String userMail = request.getParameter("userMail");
        User user = userManagementService.findId(userName, userMail);
        model.addAttribute("user", user);
        return "find_id_result";
    }

    // 아이디 찾기 결과 페이지
    @RequestMapping("/find_id_result")
    public String findIdResult() {
        return "/login";
    }

    // 비밀번호 찾기 form call
    @GetMapping("/find_pw")
    public String findPwPage() {
        return "/find_pw";
    }

    // 비밀번호 찾기 DB 조회
    @PostMapping("/find_pw")
    public String findPw(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userMail = request.getParameter("userMail");
        User user = userManagementService.findPw(userId, userName, userMail);
        if(user == null) {
            System.out.println("존재하지 않는 아이디입니다.");
            return "redirect:/find_pw";
        }
        model.addAttribute("userId", userId);
        return "/member/change_pw";
    }

    // 비밀번호 변경 페이지 form call
    @PostMapping("/change_pw")
    public String changePw(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String changePw = request.getParameter("userPw");
        User user = userManagementService.getUser(userId);

        user.setUserPw(passwordEncoder.encode(changePw));
        userManagementService.updateUser(user);

        return "/login";
    }

    // 비밀번호 확인 페이지 form call
    @GetMapping("/member/check_pw")
    public String checkPwPage(Authentication auth, Model model) {
        String currentUserId = auth.getName();
        model.addAttribute("currentUserId", currentUserId);
        log.info("userId===========" + currentUserId);
        return "/member/check_pw";
    }

    // 비밀번호 확인 -> DB 조회
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
        System.out.println("비밀번호가 올바르지 않습니다."); // 모달창?
        return "redirect:/member/check_pw";
    }


    // 더미 데이터 암호화 메소드 (시연할때 처음에 실행)
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