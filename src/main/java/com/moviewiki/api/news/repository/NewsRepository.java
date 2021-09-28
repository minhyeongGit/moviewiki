package com.moviewiki.api.news.repository;

import com.moviewiki.api.news.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
