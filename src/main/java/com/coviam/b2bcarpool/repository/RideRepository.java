package com.coviam.b2bcarpool.repository;

import java.util.List;

import com.coviam.b2bcarpool.models.TripRiders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends MongoRepository<TripRiders, String> {

  List<TripRiders> findAllByUserId(String riderUserId);

  List<TripRiders> findAllByUserIdAndRideStatus(String riderUserId, String allottedStatus);
}
