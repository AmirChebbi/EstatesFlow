package com.example.EstatesFlow.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private int phoneNumber;

    @ManyToOne
    private Company relatedCompany;

    @ManyToOne
    private Project relatedProject;

    @ManyToOne
    private Apartment wantedApartment;

    private float price;
}
