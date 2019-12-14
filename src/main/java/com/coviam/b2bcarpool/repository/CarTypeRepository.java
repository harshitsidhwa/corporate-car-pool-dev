package com.coviam.b2bcarpool.repository;

import com.coviam.b2bcarpool.models.Cars;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypeRepository extends MongoRepository<Cars, String> {
}
