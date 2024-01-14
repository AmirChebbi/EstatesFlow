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

    private String apartmentDescription;

    private String state = "For Sale";
}
