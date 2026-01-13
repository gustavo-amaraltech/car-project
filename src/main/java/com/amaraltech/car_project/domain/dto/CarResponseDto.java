package com.amaraltech.car_project.domain.dto;

import com.amaraltech.car_project.domain.entities.Car;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Map;
import java.util.UUID;

public record CarResponseDto(
        UUID id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime updatedAt,
        String vehiclePlate,
        String model,
        String brand,
        String color,
        Year manufactureYear,
        Map<String, String> additionalAttributes) {
    public static CarResponseDto toDto(Car car) {
        return new CarResponseDto(
                car.getId(),
                car.getCreatedAt(),
                car.getUpdatedAt(),
                car.getVehiclePlate(),
                car.getModel(),
                car.getBrand(),
                car.getColor(),
                car.getManufactureYear(),
                car.getAdditionalAttributes()
        );
    }
}
