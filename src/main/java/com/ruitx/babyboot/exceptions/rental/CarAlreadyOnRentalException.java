package com.ruitx.babyboot.exceptions.rental;

public class CarAlreadyOnRentalException extends RuntimeException {
    public CarAlreadyOnRentalException(String message) {
        super(message);
    }
}
