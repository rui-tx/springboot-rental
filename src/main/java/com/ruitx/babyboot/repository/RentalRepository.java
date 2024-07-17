package com.ruitx.babyboot.repository;

import com.ruitx.babyboot.model.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
    Optional<Rental> findFirstByCarIdOrderByStartDateDesc(long carId);
}
