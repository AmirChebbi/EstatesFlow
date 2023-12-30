package com.example.EstatesFlow.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String coName;

    private String imageURL;

    private String coDescription;

    @OneToMany
    private List<Project> projects ;
}
