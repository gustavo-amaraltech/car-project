package com.amaraltech.car_project;

import com.amaraltech.car_project.domain.entities.Car;
import com.amaraltech.car_project.repository.CarRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableJpaAuditing
public class CarProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CarProjectApplication.class, args);

        CarRepository carRepository = run.getBean(CarRepository.class);

        if (carRepository.findAll().isEmpty()) {

            try {
                carRepository.save(Car.builder()
                        .brand("Chevrolet")
                        .model("Onix")
                        .color("Black")
                        .manufactureYear(Year.of(2014))
                        .additionalAttributes(Map.of("power", "92cv", "fuel", "flex"))
                        .build());

                Thread.sleep(3000);

                carRepository.save(Car.builder()
                        .brand("Citroen")
                        .model("C3")
                        .color("Silver")
                        .manufactureYear(Year.of(2014))
                        .additionalAttributes(Map.of("power", "92cv", "fuel", "flex"))
                        .build());

                Thread.sleep(3000);

                carRepository.save(Car.builder()
                        .brand("Renault")
                        .model("Sandero")
                        .color("White")
                        .manufactureYear(Year.of(2016))
                        .additionalAttributes(Map.of("power", "116cv", "fuel", "flex"))
                        .build());

            } catch (InterruptedException e) {
                System.out.println("ee");
            }
        }
    }

}
