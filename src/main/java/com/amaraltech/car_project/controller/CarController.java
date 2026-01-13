package com.amaraltech.car_project.controller;

import com.amaraltech.car_project.domain.dto.CarRequestDto;
import com.amaraltech.car_project.domain.dto.CarResponseDto;
import com.amaraltech.car_project.domain.entities.Car;
import com.amaraltech.car_project.service.CarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<Page<CarResponseDto>> getCars(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) LocalDateTime updatedAt,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Year from,
            @RequestParam(required = false) Year to
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<CarResponseDto> cars = carService.findAll(pageable, updatedAt, brand, model, from, to);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDto> getCarById(@PathVariable UUID id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CarResponseDto> createCar(@RequestBody CarRequestDto carRequestDto) {
        CarResponseDto save = carService.save(carRequestDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.id()
                        .toString())
                .toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDto> updateCar(@PathVariable UUID id, @RequestBody CarRequestDto carRequestDto) {
        return ResponseEntity.ok(carService.update(id, carRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
       carService.delete(id);
       return ResponseEntity.noContent().build();
    }
}
