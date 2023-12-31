package com.example.EstatesFlow.DTO;

import com.example.EstatesFlow.DAO.Entities.Apartment;
import com.example.EstatesFlow.DAO.Entities.Project;
import com.example.EstatesFlow.Services.ApartmentService;

import java.util.List;

public class ProjectDTO {

    private long id;

    private String projName;

    private String projDescription;

    private String address;

    private String projImageURL;

    private List<Apartment> notSold;

    public ProjectDTO(Project project){
        this.id=project.getId();
        this.projName=project.getProjName();
        this.projImageURL=project.getProjImageURL();
        this.projDescription=project.getProjDescription();
        this.address=project.getAddress();
        this.notSold= ApartmentService.filterSoldApartments(project.getApartments());
    }
}
