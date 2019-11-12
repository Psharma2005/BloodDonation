package com.alokjgoyal.DonorServer.repository;

import com.alokjgoyal.DonorServer.model.BloodRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RequestRepository extends MongoRepository<BloodRequest, UUID> {

    @Query("{ 'user_id' : ?0 }")
     List<BloodRequest> findAllByUser_id(UUID user_id);

    @Query("{ 'status' : ?0 }")
    List<BloodRequest> findByStatus(String status);

}
