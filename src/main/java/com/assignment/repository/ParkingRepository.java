package com.assignment.repository;

import com.assignment.model.Parking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Default Repository for the DAO Parking
 */
@Repository
public interface ParkingRepository extends CrudRepository<Parking, Integer> {}