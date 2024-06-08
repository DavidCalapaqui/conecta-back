package com.example.conecta_test.service;

import com.example.conecta_test.entity.Car;
import com.example.conecta_test.payload.ApiResponseDto;
import com.example.conecta_test.payload.CanCirculateDto;
import com.example.conecta_test.payload.CarDto;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ICarService {

    ApiResponseDto<CarDto> createCar(CarDto carDto);

    Optional<Car> getCarByPlate(String plate);

    Optional<Car> getCarByVim(String vim);

    ApiResponseDto<CanCirculateDto> getCanCirculate(String plate, LocalDateTime dateTime);

}
