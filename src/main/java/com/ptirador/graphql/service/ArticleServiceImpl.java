package com.ptirador.graphql.service;

import com.ptirador.graphql.model.Article;
import com.ptirador.graphql.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAllUserArticles(List<String> articleIds) {
        return articleRepository.findByIdIn(articleIds);
    }
}
