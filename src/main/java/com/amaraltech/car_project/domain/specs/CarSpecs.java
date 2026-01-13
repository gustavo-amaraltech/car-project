package com.amaraltech.car_project.domain.specs;

import com.amaraltech.car_project.domain.entities.Car;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.time.Year;

public class CarSpecs {
    public static Specification<Car> modelEquals(String model) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (ObjectUtils.isEmpty(model)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("model"), model);
        };
    }

    public static Specification<Car> brandEquals(String brand) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (ObjectUtils.isEmpty(brand)) {
                return null;
            }
            return criteriaBuilder.equal(root.get("brand"), brand);
        };
    }

    public static Specification<Car> manufactureYearBetween(Year start, Year end) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (start == null && end == null) {
                return null;
            }

            if (start != null && end != null) {
                return criteriaBuilder.between(root.get("manufactureYear"), start, end);
            }

            if (start != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("manufactureYear"), start);
            }

            return criteriaBuilder.lessThanOrEqualTo(root.get("manufactureYear"), end);
        };
    }

}
