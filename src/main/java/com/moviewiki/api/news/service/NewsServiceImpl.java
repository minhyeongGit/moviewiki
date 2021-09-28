package com.moviewiki.api.news.service;

import com.moviewiki.api.news.domain.News;
import com.moviewiki.api.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> getNewsList() {
        List<News> newsList = newsRepository.findAll(Sort.by(Sort.Direction.DESC, "newsId"));
        return newsList;
    }

    @Override
    public void createNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public void deleteNews(Long newsId) {
        newsRepository.deleteById(newsId);
    }

    @Override
    public void updateNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public News getNews(Long newsId) {
       Optional<News> newsWrapper = newsRepository.findById(newsId);
       News news = newsWrapper.get();
       return news;
    }
}
