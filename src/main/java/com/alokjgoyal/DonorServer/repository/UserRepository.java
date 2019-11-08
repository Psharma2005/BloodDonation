package com.alokjgoyal.DonorServer.repository;

import com.alokjgoyal.DonorServer.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    Optional<User> findUserByEmail(String email);
}
