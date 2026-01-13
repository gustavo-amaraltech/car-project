package com.amaraltech.car_project.domain.queryFilter;

import com.amaraltech.car_project.domain.entities.Car;
import com.amaraltech.car_project.domain.specs.CarSpecs;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;

public class CarQueryFilter {

    private Specification<Car> spec;


    public CarQueryFilter model(String model) {
        spec = spec.and(CarSpecs.modelEquals(model));
        return this;
    }

    public CarQueryFilter manufactureYear(Year start, Year end) {
        spec = spec.and(CarSpecs.manufactureYearBetween(start, end));
        return this;
    }

    public CarQueryFilter brand(String brand) {
        spec = spec.and(CarSpecs.brandEquals(brand));
        return this;
    }

    public Specification<Car> build() {
        return spec;
    }
}
