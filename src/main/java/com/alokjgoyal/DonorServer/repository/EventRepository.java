package com.alokjgoyal.DonorServer.repository;

import com.alokjgoyal.DonorServer.model.DonationEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EventRepository extends MongoRepository<DonationEvent, UUID> {
}
