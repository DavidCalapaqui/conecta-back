package com.example.conecta_test.payload;

import lombok.Data;

@Data
public class CanCirculateDto {

    private boolean canCirculate;
    private CarDto carDto;

    public CanCirculateDto(boolean canCirculate, CarDto carDto) {
        this.canCirculate = canCirculate;
        this.carDto = carDto;
    }

    public boolean canCirculate() {
        return canCirculate;
    }

    public void setCanCirculate(boolean canCirculate) {
        this.canCirculate = canCirculate;
    }

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }
}
