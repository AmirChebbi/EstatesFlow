package com.example.EstatesFlow.Entities.Project;

import com.example.EstatesFlow.Entities.Apartment.Apartment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @Column(nullable = false)
    private String projName;


    private String projDescription;

    @Column(nullable = false)
    private String address;

    private String projImageURL;

    @OneToMany
    private List<Apartment> apartments;

    public Project(String projName, String projDescription, String address, String projImageURL) {
        this.projName = projName;
        this.projDescription = projDescription;
        this.address = address;
        this.projImageURL = projImageURL;
        this.apartments = new ArrayList<>();
    }
}
