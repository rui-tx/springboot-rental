package com.ruitx.babyboot.controller;

import com.ruitx.babyboot.dto.RentalDto;
import com.ruitx.babyboot.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rental")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<RentalDto>> getAll() {
        List<RentalDto> rentals = this.rentalService.getAll();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }


    @PostMapping(path = {"", "/"})
    public ResponseEntity<RentalDto> create(@Valid @RequestBody RentalDto rental) {
        RentalDto response = this.rentalService.create(rental);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    /*
    @PutMapping(path = "/{id}")
    public ResponseEntity<RentalDto> update(@Valid @PathVariable long id, @Valid @RequestBody RentalDto rental) {
        RentalDto response = this.rentalService.update(id, rental);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RentalDto> delete(@PathVariable long id) {
        RentalDto response = this.rentalService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

     */
}
