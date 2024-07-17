package com.ruitx.babyboot.aspects;

import com.ruitx.babyboot.exceptions.Error;
import com.ruitx.babyboot.exceptions.car.CarNotFoundException;
import com.ruitx.babyboot.exceptions.client.ClientNotFoundException;
import com.ruitx.babyboot.exceptions.rental.CarAlreadyOnRentalException;
import com.ruitx.babyboot.exceptions.rental.RentalNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Component
@ControllerAdvice
public class ExceptionsHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler({
            ClientNotFoundException.class,
            CarNotFoundException.class,
            RentalNotFoundException.class,
    })
    public ResponseEntity<String> HandleNotFoundException(Exception e, HttpServletRequest request) {
        logger.error("Handle not found: {}", e.getMessage());

        Error error = new Error.Builder()
                .timestamp(new Date().toString())
                .message(e.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error.toJson(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> MethodArgumentNotValidException(Exception e, HttpServletRequest request) {
        String customMessage = "An invalid argument was provided";
        logger.error("{}: {}", customMessage, e.getMessage());

        Error error = new Error.Builder()
                .timestamp(new Date().toString())
                .message(customMessage)
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error.toJson(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CarAlreadyOnRentalException.class)
    public ResponseEntity<String> CarAlreadyOnRentalException(Exception e, HttpServletRequest request) {
        logger.error("Selected car is already on rental: {}", e.getMessage());

        Error error = new Error.Builder()
                .timestamp(new Date().toString())
                .message(e.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error.toJson(), HttpStatus.CONFLICT);
    }

    // generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> GenericException(Exception e, HttpServletRequest request) {
        logger.error("An error occurred: {}", e.getMessage());
        // builder da wish
        /*
        String error = """
                {
                    "timestamp": "$timestamp",
                    "message": "$message",
                    "method": "$method",
                    "path": "$path"
                }
                """.replace("$timestamp", new Date().toString())
                .replace("$message", e.getMessage())
                .replace("$method", request.getMethod())
                .replace("$path", request.getRequestURI());
         */
        Error error = new Error.Builder()
                .timestamp(new Date().toString())
                .message(e.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error.toJson(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}