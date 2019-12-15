package com.coviam.b2bcarpool.repository;

import com.coviam.b2bcarpool.models.Riders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRideRepository extends MongoRepository<Riders, String> {
}
