package com.ruitx.babyboot.repository;

import com.ruitx.babyboot.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    public Car findByModel(String model);
}

