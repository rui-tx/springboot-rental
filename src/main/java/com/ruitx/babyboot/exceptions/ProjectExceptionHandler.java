package com.ruitx.babyboot.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
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
public class ProjectExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ProjectExceptionHandler.class);

    @ExceptionHandler({
            ClientNotFoundException.class,
            CarNotFoundException.class,
            RentalNotFoundException.class,
    })
    public ResponseEntity<String> HandleNotFoundException(Exception e, HttpServletRequest request) {
        logger.error("{}: {}", ErrorMessage.HANDLE_NOT_FOUND, e.getMessage());

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
        logger.error("{}: {}", ErrorMessage.INVALID_ARGUMENT, e.getMessage());

        Error error = new Error.Builder()
                .timestamp(new Date().toString())
                .message(ErrorMessage.INVALID_ARGUMENT)
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error.toJson(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CarAlreadyOnRentalException.class)
    public ResponseEntity<String> CarAlreadyOnRentalException(Exception e, HttpServletRequest request) {
        logger.error("{}: {}", ErrorMessage.CAR_ALREADY_ON_RENTAL, e.getMessage());

        Error error = new Error.Builder()
                .timestamp(new Date().toString())
                .message(e.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error.toJson(), HttpStatus.CONFLICT);
    }

    // json parsing exception
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> JsonProcessingException(Exception e, HttpServletRequest request) {
        logger.error("{}: {}", ErrorMessage.JSON_PARSING_ERROR, e.getMessage());
        // builder da wish
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

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> GenericException(Exception e, HttpServletRequest request) {
        logger.error("{}: {}", ErrorMessage.GENERIC_ERROR, e.getMessage());
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