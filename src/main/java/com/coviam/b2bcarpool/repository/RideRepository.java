package com.coviam.b2bcarpool.repository;

import com.coviam.b2bcarpool.models.Riders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RideRepository extends MongoRepository<Riders, String> {
    List<Riders> findByRideStartTime();

    List<Riders> findByRideStartTimeBetween(Date startTime, Date endTime);
}
