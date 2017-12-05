package com.ptirador.graphql.dataloader;

import com.ptirador.graphql.model.Article;
import com.ptirador.graphql.model.User;
import com.ptirador.graphql.repository.ArticleRepository;
import com.ptirador.graphql.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public DataLoader(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        List<User> users = new ArrayList<>();
        users.add(User.builder().name("Igor").createdAt(new Date()).age(24).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users.add(User.builder().name("Ellen").createdAt(new Date()).age(24).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users.add(User.builder().name("John").createdAt(new Date()).age(53).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users.add(User.builder().name("Nazar").createdAt(new Date()).age(14).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users = (List<User>) userRepository.save(users);

        List<Article> articles = new ArrayList<>();
        articles.add(Article.builder().title("Java 8 Lambdas").minutesRead(8).authorId(users.get(0).getId().toString()).build());
        articles.add(Article.builder().title("GraphQL Getting Started").minutesRead(10).authorId(users.get(1).getId().toString()).build());
        articles.add(Article.builder().title("Spring Boot + WebSockets").minutesRead(6).authorId(users.get(3).getId().toString()).build());
        articles = (List<Article>) articleRepository.save(articles);

        User me = users.get(0);
        users.get(0).setArticlesIds(Collections.singletonList(articles.get(0).getId().toHexString()));
        users.get(0).setArticlesIds(Collections.singletonList(articles.get(1).getId().toHexString()));
        users.get(1).setArticlesIds(Collections.singletonList(articles.get(2).getId().toHexString()));
        users.get(3).setArticlesIds(Collections.singletonList(articles.get(0).getId().toHexString()));
        userRepository.save(users);

        List<String> myFriendsIds = new ArrayList<>();
        for (int i = 1; i < users.size(); i++) {
            myFriendsIds.add(users.get(i).getId().toHexString());
        }
        me.setFriendsIds(myFriendsIds);
        userRepository.save(me);
    }
}
