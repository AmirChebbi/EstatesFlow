package com.example.EstatesFlow.DTO;

import com.example.EstatesFlow.DAO.Entities.Apartment;
import com.example.EstatesFlow.DAO.Entities.Company;
import com.example.EstatesFlow.DAO.Entities.Forum;
import com.example.EstatesFlow.DAO.Entities.Project;
import jakarta.persistence.ManyToOne;

public class ForumDTO {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private int phoneNumber;

    private String relatedCompany;

    private String relatedProject;

    private int wantedApartmentFloor;
    private int wantedApartment;

    private float price;

    public ForumDTO(Forum forum){
        this.id=forum.getId();
        this.firstName=forum.getFirstName();
        this.lastName=forum.getLastName();
        this.email=forum.getEmail();
        this.relatedCompany=forum.getRelatedCompany().getCoName();
        this.relatedProject=forum.getRelatedProject().getProjName();
        this.wantedApartmentFloor=forum.getWantedApartment().getFloorNumber();
        this.wantedApartment=forum.getWantedApartment().getApartmentNumber();
        this.price=forum.getPrice();
    }
}
