package com.ruitx.babyboot.service;

import com.ruitx.babyboot.dto.CarDto;
import com.ruitx.babyboot.exceptions.ErrorMessage;
import com.ruitx.babyboot.exceptions.car.CarNotFoundException;
import com.ruitx.babyboot.mapper.CarMapper;
import com.ruitx.babyboot.model.Car;
import com.ruitx.babyboot.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDto> getAll() {
        List<CarDto> carDtos = new ArrayList<>();

        this.carRepository.findAll()
                .forEach(e -> carDtos.add(CarMapper.toDto(e)));
        return carDtos;
    }

    public CarDto create(CarDto car) {
        Car newCar = CarMapper.fromDto(car);
        this.carRepository.save(newCar);
        return CarMapper.toDto(newCar);
    }

    public CarDto update(long id, CarDto car) {
        Car carToUpdate;
        carToUpdate = this.carRepository.findById(id).orElseThrow(
                () -> new CarNotFoundException(ErrorMessage.CAR_NOT_FOUND)
        );

        // TODO: Is this the right way to update?
        carToUpdate.setModel(car.model());
        carToUpdate.setModel(car.model());
        carToUpdate.setYear(car.year());
        carToUpdate.setKms(car.kms());

        // TODO: Assuming that everything is ok with the update
        this.carRepository.save(carToUpdate);
        return CarMapper.toDto(carToUpdate);
    }

    public CarDto delete(long id) {
        Car carToDelete = this.carRepository.findById(id).orElseThrow(
                () -> new CarNotFoundException(ErrorMessage.CAR_NOT_FOUND)
        );

        this.carRepository.delete(carToDelete);
        return CarMapper.toDto(carToDelete);
    }
}
