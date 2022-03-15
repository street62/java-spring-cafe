package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public Article findById(int id) {
        return articleRepository.findByid(id).orElseThrow(() -> new IllegalStateException("해당하는 글이 없습니다."));
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
