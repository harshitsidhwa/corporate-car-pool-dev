package com.coviam.b2bcarpool.repository;

import java.util.Date;
import java.util.List;

import com.coviam.b2bcarpool.models.Riders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends MongoRepository<Riders, String> {

  List<Riders> findAllByUserId(String riderUserId);

  List<Riders> findAllByUserIdAndRideStatus(String riderUserId, String allottedStatus);

  List<Riders> findByRideStartTime();

  List<Riders> findByRideStartTimeBetween(Date startTime, Date endTime);

  List<Riders> findByUserIdAndRideStatus(String riderUserId, String allottedStatus);
}
