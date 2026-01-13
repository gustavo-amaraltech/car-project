package com.amaraltech.car_project.service;

import com.amaraltech.car_project.domain.entities.Car;
import com.amaraltech.car_project.domain.dto.CarRequestDto;
import com.amaraltech.car_project.domain.dto.CarResponseDto;
import com.amaraltech.car_project.domain.specs.CarSpecs;
import com.amaraltech.car_project.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {

    private final CarRepository carRepository;

    public Page<CarResponseDto> findAll(Pageable pageable, LocalDateTime updatedAt, String brand, String model, Year start, Year end) {

        Specification<Car> spec = Specification
                .where(CarSpecs.modelEquals(model))
                .and(CarSpecs.updatedAtGreaterThanOrEqualTo(updatedAt))
                .and(CarSpecs.manufactureYearBetween(start, end))
                .and(CarSpecs.brandEquals(brand));

        return carRepository.findAll(spec, pageable)
                .map(CarResponseDto::toDto);
    }

    public CarResponseDto findById(UUID id) {
        return CarResponseDto.toDto(carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found")));
    }

    public CarResponseDto save(CarRequestDto car) {
        return CarResponseDto.toDto(carRepository.save(CarRequestDto.toEntity(car)));
    }

    @Transactional
    public CarResponseDto update(UUID id, CarRequestDto car) {

        Car toUpdate = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        Optional.ofNullable(car.vehiclePlate()).ifPresent(toUpdate::setVehiclePlate);
        Optional.ofNullable(car.brand()).ifPresent(toUpdate::setBrand);
        Optional.ofNullable(car.model()).ifPresent(toUpdate::setModel);
        Optional.ofNullable(car.color()).ifPresent(toUpdate::setColor);
        Optional.ofNullable(car.manufactureYear()).ifPresent(toUpdate::setManufactureYear);

        if (Boolean.TRUE.equals(car.replaceAdditionalAttributes())) {
            toUpdate.setAdditionalAttributes(
                    car.additionalAttributes() != null
                            ? new HashMap<>(car.additionalAttributes())
                            : new HashMap<>()
            );

        } else if (car.additionalAttributes() != null) {

            car.additionalAttributes().forEach(
                    toUpdate.getAdditionalAttributes()::put
            );
        }

        return CarResponseDto.toDto(carRepository.save(toUpdate));
    }

    public void delete( UUID id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        carRepository.delete(car);
    }
}
