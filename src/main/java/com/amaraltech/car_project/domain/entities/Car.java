package com.amaraltech.car_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.*;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String model;

    private String brand;

    private String color;

    private Year manufactureYear;

    @ElementCollection
    @CollectionTable(
            name = "car_additional_attributes",
            joinColumns = @JoinColumn(name = "car_id")
    )
    @MapKeyColumn(name = "attr_key")
    @Column(name = "attr_value")
    private Map<String, String> additionalAttributes = new HashMap<>();
}
