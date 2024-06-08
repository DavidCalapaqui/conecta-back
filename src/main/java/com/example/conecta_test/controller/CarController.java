package com.example.conecta_test.controller;


import com.example.conecta_test.payload.ApiResponseDto;
import com.example.conecta_test.payload.CanCirculateDto;
import com.example.conecta_test.payload.CarDto;
import com.example.conecta_test.service.ICarService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private  ICarService carService;
    public CarController(ICarService carService){
        this.carService = carService;
    }

    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<ApiResponseDto<CarDto>> createCar(@Valid @RequestBody CarDto carDto){
        ApiResponseDto<CarDto> car = carService.createCar(carDto);
        //ApiResponseDto<CarDto> apiResponse = new ApiResponseDto<CarDto>(true, car, "Auto creado correctamente");
        return new ResponseEntity<ApiResponseDto<CarDto>>(car, car.getHttpStatus());
    }

    @GetMapping("{plate}")
    public ResponseEntity<ApiResponseDto<CanCirculateDto>> getCanCirculate(
            @Validated
            @PathVariable("plate") String plate,
            @RequestParam("dateTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dateTime
            ){
        System.out.println("Plate: "+ plate);
        System.out.println("DateTime: "+ dateTime);
        ApiResponseDto<CanCirculateDto> canCirculate = carService.getCanCirculate(plate, dateTime);
        return new ResponseEntity<ApiResponseDto<CanCirculateDto>>(canCirculate, canCirculate.getHttpStatus());
    }






}
