package com.moviewiki.api.news.controller;

import com.moviewiki.api.news.domain.News;
import com.moviewiki.api.news.service.NewsService;
import com.moviewiki.api.user.controller.UserManagementController;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor
@Controller
public class NewsController {
    private static final Logger log = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    private NewsService newsService;

    // 비로그인 뉴스게시판 페이지 이동
    @RequestMapping("/anonymousNews")
    public String initNewsPage(Model model) {
        List<News> newsList = newsService.getNewsList();
        model.addAttribute("newsList", newsList);
        return "news";
    }

    // 로그인 뉴스게시판 페이지 이동
    @RequestMapping("/news")
    public String newsPage(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser) {
        List<News> newsList = newsService.getNewsList();
        model.addAttribute("currentUserId", currentUser.getUsername());
        model.addAttribute("newsList", newsList);
        return "news";
    }

    // 뉴스 등록 form call
    @GetMapping("/createNews")
    public String createNewsPage(Model model) {
        model.addAttribute("news", new News());
        return "admin/admin_news_add";
    }


    // 글쓰기 -> DB 저장 메소드
    @PostMapping("/createNews")
    public String createNews(@ModelAttribute News news, HttpServletRequest request) {
        log.info("createNews");
        newsService.createNews(news);
        return "redirect:/admin/admin_news";
    }

    // 뉴스목록 보여주는 메소드
    @GetMapping("/admin/admin_news")
    public String newsList(Model model) {
        List<News> newsList = newsService.getNewsList();
        model.addAttribute("newsList", newsList);
        return "admin/admin_news";
    }

    // 뉴스 삭제
    @RequestMapping("/deleteNews/{newsId}")
    public String deleteNews(@PathVariable Long newsId) {
        log.info("newsid = "+ newsId);
        newsService.deleteNews(newsId);
        return "redirect:/admin/admin_news";
    }

    // 뉴스 수정
    @RequestMapping("/updateNews/{newsId}")
    public String updateNewsPage(@PathVariable Long newsId, Model model) {
        log.info("newsid = "+ newsId);
        News news = newsService.getNews(newsId);
        model.addAttribute("news", news);
        return "/admin/admin_news_modify";
    }

    // 글수정 -> DB 저장 메소드
    @PostMapping("/updateNews/{newsId}")
    public String updateNews(News news) {
        log.info("news========" + news);
        newsService.updateNews(news);
        return "redirect:/admin/admin_news";
    }

    // 비로그인 뉴스 상세 보기
    @RequestMapping("/anonymousReadNews/{newsId}")
    public String initReadNews(@PathVariable Long newsId, Model model) {
        log.info("newsid = "+ newsId);
        News news = newsService.getNews(newsId);
        model.addAttribute("news", news);
        return "/news_detail";
    }

    // 로그인 뉴스 상세 보기
    @RequestMapping("/readNews/{newsId}")
    public String readNews(@PathVariable Long newsId, Model model, @AuthenticationPrincipal User currentUser) {
        log.info("newsid = "+ newsId);
        News news = newsService.getNews(newsId);
        model.addAttribute("news", news);
        model.addAttribute("currentUserId", currentUser.getUsername());
        return "/news_detail";
    }
}
