package com.ruitx.babyboot.mapper;

import com.ruitx.babyboot.dto.CarDto;
import com.ruitx.babyboot.model.Car;

public class CarMapper {
    public static CarDto toDto(Car car) {
        return new CarDto(car.getModel(), car.getYear(), car.getKms());
    }

    public static Car fromDto(CarDto carDto) {
        Car car = new Car();
        car.setModel(carDto.model());
        car.setYear(carDto.year());
        car.setKms(carDto.kms());
        return car;
    }
}
