package com.ptirador.graphql.service;

import com.ptirador.graphql.model.User;
import com.ptirador.graphql.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findOneById(ObjectId id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findByIdIn(List<String> friendsIds) {
        return userRepository.findByIdIn(friendsIds);
    }
}
