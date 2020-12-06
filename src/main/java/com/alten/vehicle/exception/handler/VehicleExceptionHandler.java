package com.alten.vehicle.exception.handler;


import com.alten.vehicle.dto.ExceptionDto;
import com.alten.vehicle.exception.NoVehicleFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VehicleExceptionHandler {


    @ExceptionHandler(NoVehicleFoundException.class)
    public ResponseEntity<ExceptionDto> handle(NoVehicleFoundException e) {
        HttpStatus responseCode = HttpStatus.NOT_FOUND;
        return createResponseEntity(e, responseCode);
    }

    private ResponseEntity createResponseEntity(Exception e,
                                                HttpStatus responseCode) {
        ExceptionDto error = new ExceptionDto(e.getMessage());
        return new ResponseEntity(error, responseCode);
    }

}
