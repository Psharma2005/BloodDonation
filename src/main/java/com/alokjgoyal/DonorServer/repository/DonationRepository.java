package com.alokjgoyal.DonorServer.repository;

import com.alokjgoyal.DonorServer.model.BloodDonation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DonationRepository extends MongoRepository<BloodDonation, UUID> {

    @Query("{ 'user_id' : ?0 }")
    List<BloodDonation> findAllByUserId(UUID user_id);

}
