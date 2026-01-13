package com.amaraltech.car_project.domain.dto;

import com.amaraltech.car_project.domain.entities.Car;

import java.time.Year;
import java.util.Map;

public record CarRequestDto(
        String vehiclePlate,
        String model,
        String brand,
        String color,
        Year manufactureYear,
        Boolean replaceAdditionalAttributes,
        Map<String, String> additionalAttributes) {
    public static Car toEntity(CarRequestDto dto) {
        return Car.builder()
                .vehiclePlate(dto.vehiclePlate)
                .model(dto.model())
                .brand(dto.brand())
                .color(dto.color())
                .manufactureYear(dto.manufactureYear())
                .additionalAttributes(dto.additionalAttributes())
                .build();
    }
}
