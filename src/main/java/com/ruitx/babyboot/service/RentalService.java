package com.ruitx.babyboot.service;

import com.ruitx.babyboot.dto.RentalDto;
import com.ruitx.babyboot.exceptions.ErrorMessage;
import com.ruitx.babyboot.exceptions.car.CarNotFoundException;
import com.ruitx.babyboot.exceptions.client.ClientNotFoundException;
import com.ruitx.babyboot.exceptions.rental.CarAlreadyOnRentalException;
import com.ruitx.babyboot.mapper.RentalMapper;
import com.ruitx.babyboot.model.Car;
import com.ruitx.babyboot.model.Client;
import com.ruitx.babyboot.model.Rental;
import com.ruitx.babyboot.repository.CarRepository;
import com.ruitx.babyboot.repository.ClientRepository;
import com.ruitx.babyboot.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        this.carRepository.findById(car.getId()).orElseThrow(
                () -> new CarNotFoundException(ErrorMessage.CAR_NOT_FOUND)
        );

        this.clientRepository.findById(client.getId()).orElseThrow(
                () -> new ClientNotFoundException(ErrorMessage.CLIENT_NOT_FOUND)
        );

        // TODO: Check if the rental car is not already on a rental
        /*
        Optional<Rental> checkIfCarisAlreadyOnRental = this.rentalRepository.findById(car.getId());
        if (checkIfCarisAlreadyOnRental.isPresent()) {
            throw new EntityNotFoundException("Car already on rental");
        }
         */
        Optional<Rental> carLatestRental = this.rentalRepository.findFirstByCarIdOrderByStartDateDesc(car.getId());

        if (carLatestRental.isPresent() && carLatestRental.get().getEndDate() == null) {
            throw new CarAlreadyOnRentalException(ErrorMessage.CAR_ALREADY_ON_RENTAL);
        }
        /*
        this.rentalRepository.findAll().forEach(e -> {
            if (e.getEndDate() != null) {
                availableCarList.add(e.getCar());
            }
        });

        if (!availableCarList.contains(car)) {
            throw new EntityNotFoundException("Car already on rental");
        }

         */

        // TODO: Check if the start date is before the end date

        newRental.setCar(car);
        newRental.setClient(client);
        newRental.setStartDate(rental.startDate());
        newRental.setEndDate(rental.endDate());

        this.rentalRepository.save(newRental);
        return RentalMapper.toDto(newRental);
    }
}
