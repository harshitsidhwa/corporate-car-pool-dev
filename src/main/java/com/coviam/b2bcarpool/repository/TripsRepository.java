package com.coviam.b2bcarpool.repository;

import java.util.Date;
import java.util.List;

import com.coviam.b2bcarpool.models.Trips;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripsRepository extends MongoRepository<Trips, String> {

  List<Trips> findAllByUserId(String riderUserId);

  List<Trips> findAllByUserIdAndTripStatus(String riderUserId, String activeStatus);

  List<Trips> findByTripStartTimeBetween(Date dateTimeBeforeNow, Date dateTimeAfterNow);
}
