package com.example.EstatesFlow.Entities.Company;

import com.example.EstatesFlow.Entities.Project.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @Column(nullable = false, unique = true)
    private String coName;

    private String imageURL;

    private String coDescription;

    @OneToMany
    private List<Project> projects ;

    public Company(String coName, String imageURL, String coDescription) {
        this.coName = coName;
        this.imageURL = imageURL;
        this.coDescription = coDescription;
        this.projects = new ArrayList<>();
    }
}
