package com.example.EstatesFlow.Entities.Apartment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int apartmentNumber;

    private int floorNumber;

    @Column(nullable = false, unique = true)
    private String apartmentDescription;

    private boolean sold;

    public Apartment(int apartmentNumber, int floorNumber, String apartmentDescription) {
        this.apartmentNumber = apartmentNumber;
        this.floorNumber = floorNumber;
        this.apartmentDescription = apartmentDescription;
        this.sold = false;
    }
}
