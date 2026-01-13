package com.amaraltech.car_project.controller;

import com.amaraltech.car_project.domain.dto.CarRequestDto;
import com.amaraltech.car_project.domain.dto.CarResponseDto;
import com.amaraltech.car_project.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cars")
@RequiredArgsConstructor
@Tag(name = "Cars", description = "API for managing cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    @Operation(summary = "Get a paginated list of cars", description = "Retrieve a paginated list of cars with optional filters by brand, model, manufacture year, and update date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of cars")
    })
    public ResponseEntity<Page<CarResponseDto>> getCars(
            @Parameter(description = "Page number, starting from 0") @RequestParam(defaultValue = "0") Integer pageNumber,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "20") Integer pageSize,
            @Parameter(description = "Filter cars updated after this date") @RequestParam(required = false) LocalDateTime updatedAt,
            @Parameter(description = "Filter by car brand") @RequestParam(required = false) String brand,
            @Parameter(description = "Filter by car model") @RequestParam(required = false) String model,
            @Parameter(description = "Filter cars manufactured from this year") @RequestParam(required = false) Year from,
            @Parameter(description = "Filter cars manufactured until this year") @RequestParam(required = false) Year to
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CarResponseDto> cars = carService.findAll(pageable, updatedAt, brand, model, from, to);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get car by ID", description = "Retrieve a specific car by its UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car found and returned successfully"),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    public ResponseEntity<CarResponseDto> getCarById(
            @Parameter(description = "UUID of the car to retrieve") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new car", description = "Create a new car and return its details along with the URI of the created resource.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car created successfully")
    })
    public ResponseEntity<CarResponseDto> createCar(
            @Parameter(description = "Car data to create") @RequestBody CarRequestDto carRequestDto
    ) {
        CarResponseDto save = carService.save(carRequestDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.id().toString())
                .toUri();
        return ResponseEntity.created(uri).body(save);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a car", description = "Update an existing car by its UUID with the provided data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car updated successfully"),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    public ResponseEntity<CarResponseDto> updateCar(
            @Parameter(description = "UUID of the car to update") @PathVariable UUID id,
            @Parameter(description = "Updated car data") @RequestBody CarRequestDto carRequestDto
    ) {
        return ResponseEntity.ok(carService.update(id, carRequestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a car", description = "Delete a car by its UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "UUID of the car to delete") @PathVariable UUID id
    ) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
