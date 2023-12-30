package com.example.EstatesFlow.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String projName;

    private String projDescription;

    private String address;

    private String projImageURL;

    @OneToMany
    private List<Apartment> apartments;
}
