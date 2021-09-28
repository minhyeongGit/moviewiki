package com.moviewiki.api.news.service;


import com.moviewiki.api.news.domain.News;

import java.util.List;

public interface NewsService {
    List<News> getNewsList();
    void createNews(News news);
    void deleteNews(Long newsId);
    News getNews(Long newsId);
    void updateNews(News news);
}
