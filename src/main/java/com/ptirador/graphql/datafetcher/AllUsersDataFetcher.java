package com.ptirador.graphql.datafetcher;

import com.ptirador.graphql.model.User;
import com.ptirador.graphql.service.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>> {

    private final UserService userService;

    public AllUsersDataFetcher(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> get(DataFetchingEnvironment env) {
        User user = env.getSource();
        List<User> friends;
        if (user != null) {
            friends = userService.findByIdIn(user.getFriendsIds());
        } else {
            friends = userService.findAllUsers();
        }
        return friends;
    }
}
