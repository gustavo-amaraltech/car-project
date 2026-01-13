package com.amaraltech.car_project.domain.dto;

import com.amaraltech.car_project.domain.entities.Car;

import java.time.Year;
import java.util.Map;
import java.util.UUID;

public record CarResponseDto(UUID id, String model, String brand, String color, Year manufactureYear, Map<String, String> additionalAttributes) {
    public static CarResponseDto toDto(Car car) {
        return new CarResponseDto(
                car.getId(),
                car.getModel(),
                car.getBrand(),
                car.getColor(),
                car.getManufactureYear(),
                car.getAdditionalAttributes()
        );
    }
}
