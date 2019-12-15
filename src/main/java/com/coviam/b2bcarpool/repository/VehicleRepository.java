package com.coviam.b2bcarpool.repository;

import com.coviam.b2bcarpool.models.Vehicles;
import com.google.maps.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicles, String> {
    List<Vehicles> findByUserId(String userId);

    boolean existsByVehicleNumber(String toUpperCase);
}
