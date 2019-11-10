package com.alokjgoyal.DonorServer.repository;

import com.alokjgoyal.DonorServer.model.BloodRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RequestRepository extends MongoRepository<BloodRequest, UUID> {

}
