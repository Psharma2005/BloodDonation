package com.alokjgoyal.DonorServer.repository;

import com.alokjgoyal.DonorServer.model.BloodDonation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DonationRepository extends MongoRepository<BloodDonation, UUID> {



}
