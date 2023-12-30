package com.example.EstatesFlow.Controllers;

import com.example.EstatesFlow.DAO.Entities.Project;
import com.example.EstatesFlow.DTO.ProjectDTO;
import com.example.EstatesFlow.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/getById/{id}")
    public Project getById(@PathVariable Long id){
        return projectService.getById(id);
    }

    @GetMapping("/getAllForSale")
    public Vector<ProjectDTO> getAllForSale(){
        return projectService.getAllForSale();
    }

    @GetMapping("/getAll")
    public List<Project> getAll(){
        return projectService.getAll();
    }

    @PostMapping("/add")
    public Project addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @PutMapping("/update/{id}")
    public Project updateProject(@PathVariable long id, @RequestBody Project project){
        project.setId(id);
        return projectService.updateProject(id ,project);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        projectService.deleteById(id);
    }
}

