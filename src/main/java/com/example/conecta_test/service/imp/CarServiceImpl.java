package com.example.conecta_test.service.imp;
import com.example.conecta_test.entity.Car;
import com.example.conecta_test.payload.ApiResponseDto;
import com.example.conecta_test.payload.CanCirculateDto;
import com.example.conecta_test.payload.CarDto;
import com.example.conecta_test.repository.ICarRepository;
import com.example.conecta_test.service.ICarService;
import com.example.conecta_test.utils.Circulation;
import com.example.conecta_test.utils.ValidateCarPlate;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CarServiceImpl implements ICarService {

    private ICarRepository carRepository;


    public CarServiceImpl(ICarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public ApiResponseDto<CarDto> createCar(CarDto carDto) {
        //VALIDAR POR PLACA Y/O CHASIS
        Car car = mapToEntity(carDto);
        Optional<Car> carByPlate = carRepository.findByPlate(car.getPlate());
        Optional<Car> carByVim = carRepository.findByVim(car.getVim());

        if(!ValidateCarPlate.validatePlate(carDto.getPlate())){
            return new ApiResponseDto<CarDto>(false, null, "Formato de placas incorrecto", HttpStatus.BAD_REQUEST);
        }
        if(carByPlate.isPresent()){
            return new ApiResponseDto<CarDto>(false, null, "Ya existe un vehículo de placas "+ carByPlate.get().getPlate(), HttpStatus.BAD_REQUEST);
        }
        if(carByVim.isPresent()){
            return new ApiResponseDto<CarDto>(false, null, "Error: el numero de chasis "+ carByVim.get().getVim() + " ya se encuentra registrado", HttpStatus.BAD_REQUEST);
        }
        Car newCar = carRepository.save(car);
        CarDto newCarDto = mapToDto(newCar);
        return new ApiResponseDto<CarDto>(true, newCarDto, "Vehículo creado correctamente", HttpStatus.CREATED);
    }

    @Override
    public Optional<Car> getCarByPlate(String plate) {
        return this.carRepository.findByPlate(plate);
    }

    @Override
    public Optional<Car> getCarByVim(String vim) {
        return this.carRepository.findByVim(vim);
    }

    @Override
    public ApiResponseDto<CanCirculateDto> getCanCirculate(String plate, LocalDateTime dateTime) {
        Optional<Car> car = carRepository.findByPlate(plate);

        if(!Circulation.validateDate(dateTime)){
            return new ApiResponseDto<CanCirculateDto>(false, null, "Fecha inválida", HttpStatus.BAD_REQUEST);
        }
        if(!ValidateCarPlate.validatePlate(plate)){
            return new ApiResponseDto<CanCirculateDto>(false, null, "Formato de placas incorrecto", HttpStatus.BAD_REQUEST);
        }
        if(!car.isPresent()){
            return new ApiResponseDto<CanCirculateDto>(false, null, "No se encontro un vehículo de placas "+ plate, HttpStatus.BAD_REQUEST);
        }

        String lastDigitStr = String.valueOf(plate.charAt(plate.length()-1));
        Integer lastDigit = Integer.parseInt(lastDigitStr);

        System.out.println("Last digit "+ lastDigit);

        boolean canCirculate = Circulation.canCirculateToday(lastDigit, dateTime);
        Car carEntity = car.get();

        CanCirculateDto canCirculateOnDate = new CanCirculateDto(  canCirculate, this.mapToDto(carEntity));

        return new ApiResponseDto<CanCirculateDto>(true, canCirculateOnDate, "Consultado correctamente", HttpStatus.OK);
    }

    private Car mapToEntity(CarDto carDto){
        Car car = new Car();
        car.setAutomaker( carDto.getAutomaker().toUpperCase() );
        car.setColor(carDto.getColor().toUpperCase());
        car.setModel(carDto.getModel().toUpperCase());
        car.setVim(carDto.getVim().toUpperCase());
        car.setPlate(carDto.getPlate().toUpperCase());
        return car;
    }

    private CarDto mapToDto(Car car){
       CarDto carDto = new CarDto();
       carDto.setPlate(car.getPlate());
       carDto.setAutomaker(car.getAutomaker());
       carDto.setColor(car.getColor());
       carDto.setVim(car.getVim());
       carDto.setModel(car.getModel());
       carDto.setId(car.getId());
       return carDto;
    }
}
