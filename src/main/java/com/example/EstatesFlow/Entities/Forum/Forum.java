package com.example.EstatesFlow.Entities.Forum;

import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Services.UserEntity.UserEntity;
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

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private Company relatedCompany;

    @ManyToOne
    private Project relatedProject;

    @ManyToOne
    private Apartment wantedApartment;

    private float price;
}
