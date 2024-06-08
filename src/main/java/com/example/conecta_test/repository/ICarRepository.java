package com.example.conecta_test.repository;

import com.example.conecta_test.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByPlate(String plate);
    Optional<Car> findByVim(String vim);
}
