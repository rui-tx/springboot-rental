package com.ruitx.babyboot.controller;

import com.ruitx.babyboot.dto.CarDto;
import com.ruitx.babyboot.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<CarDto>> getAll() {
        List<CarDto> cars = this.carService.getAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<CarDto> create(@Valid @RequestBody CarDto car) {
        CarDto response = this.carService.create(car);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CarDto> update(@Valid @PathVariable long id, @Valid @RequestBody CarDto car) {
        CarDto response = this.carService.update(id, car);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CarDto> delete(@PathVariable long id) {
        CarDto response = this.carService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
