package com.coviam.b2bcarpool.repository;

import com.coviam.b2bcarpool.models.Trips;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TripsRepository extends MongoRepository<Trips, String> {
    List<Trips> findByTripStartTimeBetween(Date dateTimeBeforeNow, Date dateTimeAfterNow);
}
