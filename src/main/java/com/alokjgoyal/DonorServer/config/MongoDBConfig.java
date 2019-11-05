package com.alokjgoyal.DonorServer.config;

import com.alokjgoyal.DonorServer.repository.DonorRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = DonorRepository.class)
@Configuration
public class MongoDBConfig {
}
