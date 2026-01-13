package com.amaraltech.car_project;

import com.amaraltech.car_project.domain.entities.Car;
import com.amaraltech.car_project.repository.CarRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CarProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CarProjectApplication.class, args);

        CarRepository carRepository = run.getBean(CarRepository.class);

        if (carRepository.findAll().isEmpty()) {
            List<Car> cars = List.of(
                    Car.builder()
                            .brand("Chevrolet")
                            .model("Onix")
                            .color("Black")
                            .manufactureYear(Year.of(2014))
                            .additionalAttributes(Map.of("power", "92cv", "year", "2014"))
                            .build(),
                    Car.builder()
                            .brand("Renault")
                            .model("Sandero")
                            .color("White")
                            .manufactureYear(Year.of(2016))
                            .additionalAttributes(Map.of("power", "92cv", "year", "2014"))
                            .build(),
                    Car.builder()
                            .brand("Citroen")
                            .model("C3")
                            .color("Silver")
                            .manufactureYear(Year.of(2014))
                            .additionalAttributes(Map.of("power", "92cv", "year", "2014"))
                            .build());

            carRepository.saveAll(cars);
        }
    }

}
