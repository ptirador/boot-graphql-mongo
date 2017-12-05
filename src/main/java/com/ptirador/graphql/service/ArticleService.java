package com.ptirador.graphql.service;

import com.ptirador.graphql.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAllUserArticles(List<String> articleIds);
}
