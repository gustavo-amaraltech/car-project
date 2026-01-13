package com.amaraltech.car_project.config;

import com.amaraltech.car_project.domain.entities.Car;
import com.amaraltech.car_project.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Year;
import java.util.Map;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(CarRepository carRepository) {
        return args -> {
            if (carRepository.findAll().isEmpty()) {
                carRepository.save(Car.builder()
                        .brand("Chevrolet")
                        .model("Onix")
                        .color("Black")
                        .manufactureYear(Year.of(2014))
                        .additionalAttributes(Map.of("power", "92cv", "fuel", "flex"))
                        .build());

                carRepository.save(Car.builder()
                        .brand("Citroen")
                        .model("C3")
                        .color("Silver")
                        .manufactureYear(Year.of(2014))
                        .additionalAttributes(Map.of("power", "92cv", "fuel", "flex"))
                        .build());

                carRepository.save(Car.builder()
                        .brand("Renault")
                        .model("Sandero")
                        .color("White")
                        .manufactureYear(Year.of(2016))
                        .additionalAttributes(Map.of("power", "116cv", "fuel", "flex"))
                        .build());
            }
        };
    }
}
