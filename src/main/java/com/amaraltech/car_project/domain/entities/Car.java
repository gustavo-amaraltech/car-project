package com.amaraltech.car_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(unique = true)
    private String vehiclePlate;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
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
