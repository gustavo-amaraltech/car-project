package com.amaraltech.car_project.controller;

import com.amaraltech.car_project.domain.dto.CarRequestDto;
import com.amaraltech.car_project.domain.dto.CarResponseDto;
import com.amaraltech.car_project.domain.entities.Car;
import com.amaraltech.car_project.repository.CarRepository;
import com.amaraltech.car_project.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarResponseDto>> getCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Year from,
            @RequestParam(required = false) Year to
    ) {
        return ResponseEntity.ok(carService.findAll(brand, model, from, to));
    }
}
