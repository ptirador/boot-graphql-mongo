package com.ptirador.graphql.service;

import com.ptirador.graphql.model.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User findOneById(ObjectId id);

    List<User> findByIdIn(List<String> friendsIds);
}
