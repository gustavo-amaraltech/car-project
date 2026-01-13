package com.amaraltech.car_project.service;

import com.amaraltech.car_project.domain.entities.Car;
import com.amaraltech.car_project.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.util.Map;

@SpringBootTest
class CarServiceTest {


    @Autowired
    private CarRepository carRepository;

   @Test
    void saveCar() {
        Car car = Car.builder()
                .brand("Citroen")
                .model("C3")
                .color("Silver")
                .manufactureYear(Year.of(2014))
                .additionalAttributes(Map.of("power", "92cv", "year", "2014"))
                .build();

       Car save = carRepository.save(car);
       System.out.println(save);
   }
}