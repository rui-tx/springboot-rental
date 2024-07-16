package com.ruitx.babyboot.service;

import com.ruitx.babyboot.dto.RentalDto;
import com.ruitx.babyboot.mapper.RentalMapper;
import com.ruitx.babyboot.model.Car;
import com.ruitx.babyboot.model.Client;
import com.ruitx.babyboot.model.Rental;
import com.ruitx.babyboot.repository.CarRepository;
import com.ruitx.babyboot.repository.ClientRepository;
import com.ruitx.babyboot.repository.RentalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(ClientRepository clientService, CarRepository carService, RentalRepository rentalRepository) {
        this.clientRepository = clientService;
        this.carRepository = carService;
        this.rentalRepository = rentalRepository;
    }

    public List<RentalDto> getAll() {
        List<RentalDto> rentalDtos = new ArrayList<>();

        this.rentalRepository.findAll()
                .forEach(e -> rentalDtos.add(RentalMapper.toDto(e)));
        return rentalDtos;
    }

    public RentalDto create(RentalDto rental) {
        Rental newRental = RentalMapper.fromDto(rental);

        Car car = newRental.getCar();
        Client client = newRental.getClient();

        // TODO: This WILL not work
        // We need another data field, for example, the client email that is UNIQUE to it
        // THen we can check with that field and see if the client exists
        // This is to avoid adding a sensitive information oh the DTO
        // Or just raw dog it and send it anyways

        // Chek if the client exists and car exists
        this.clientRepository.findById(client.getId()).orElseThrow(
                () -> new EntityNotFoundException("Client not found")
        );

        this.carRepository.findById(car.getId()).orElseThrow(
                () -> new EntityNotFoundException("Car not found")
        );

        // TODO: Check if the rental car is not already on a rental
        // TODO: Check if the start date is before the end date

        newRental.setCar(car);
        newRental.setClient(client);
        newRental.setStartDate(rental.startDate());
        newRental.setEndDate(rental.endDate());

        this.rentalRepository.save(newRental);
        return RentalMapper.toDto(newRental);
    }
}
