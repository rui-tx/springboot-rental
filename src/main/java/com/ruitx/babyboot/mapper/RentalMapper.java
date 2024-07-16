package com.ruitx.babyboot.mapper;

import com.ruitx.babyboot.dto.RentalDto;
import com.ruitx.babyboot.model.Rental;

public class RentalMapper {
    public static RentalDto toDto(Rental rental) {
        return new RentalDto(
                ClientMapper.toDto(rental.getClient()),
                CarMapper.toDto(rental.getCar()),
                rental.getStartDate(),
                rental.getEndDate());
    }

    public static Rental fromDto(RentalDto rentalDto) {
        Rental rental = new Rental();
        rental.setClient(ClientMapper.fromDto(rentalDto.client()));
        rental.setCar(CarMapper.fromDto(rentalDto.car()));
        rental.setStartDate(rentalDto.startDate());
        rental.setEndDate(rentalDto.endDate());
        return rental;
    }
}
