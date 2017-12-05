package com.ptirador.graphql.repository;

import com.ptirador.graphql.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, ObjectId> {
    List<User> findByIdIn(List<String> ids);
}
