package com.example.EstatesFlow.Services;

import com.example.EstatesFlow.DAO.Entities.Apartment;
import com.example.EstatesFlow.DAO.Entities.Project;
import com.example.EstatesFlow.DAO.Repositories.ApartmentRepository;
import com.example.EstatesFlow.DAO.Repositories.ProjectRepository;
import com.example.EstatesFlow.DTO.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Vector;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getById(@PathVariable Long id){
        return projectRepository.getById(id);
    }

    public List<Project> getAll(){
        return projectRepository.findAll();
    }


    public Vector<ProjectDTO> getAllForSale(){
        Vector<ProjectDTO> projectDTOS = new Vector<>();
        List<Project> projects=  projectRepository.findAll();
        for (Project project : projects){
            projectDTOS.add(new ProjectDTO(project));
        }
        return projectDTOS;
    }

    public Project addProject(@RequestBody Project project){
        return projectRepository.save(project);
    }

    public Project updateProject(@PathVariable long id, @RequestBody Project project){
        project.setId(id);
        return projectRepository.save(project);
    }

    public void deleteById(@PathVariable long id) {
        projectRepository.deleteById(id);
    }

    public static Vector<String> mapProjectsToNames(List<Project> projects){
        Vector<String> names =new Vector<>();
        for (Project project : projects){
            names.add(project.getProjName());
        }
        return names;
    }

}
