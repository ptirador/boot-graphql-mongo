package com.ptirador.graphql.datafetcher;

import com.ptirador.graphql.model.User;
import com.ptirador.graphql.service.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserDataFetcher implements DataFetcher<User> {

    private final UserService userService;

    public UserDataFetcher(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public User get(DataFetchingEnvironment env) {
        Map<String, Object> args = env.getArguments();
        return userService.findOneById(new ObjectId(String.valueOf(args.get("id"))));
    }
}
