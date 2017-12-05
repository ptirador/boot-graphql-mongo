package com.ptirador.graphql.datafetcher;

import com.ptirador.graphql.model.Article;
import com.ptirador.graphql.model.User;
import com.ptirador.graphql.service.ArticleService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticlesDataFetcher implements DataFetcher<List<Article>> {

    private final ArticleService articleService;

    public ArticlesDataFetcher(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public List<Article> get(DataFetchingEnvironment env) {
        User user = env.getSource();
        List<String> articleIds = new ArrayList<>();
        if (user != null) {
            articleIds = user.getArticlesIds();
        }
        return articleService.findAllUserArticles(articleIds);
    }
}
