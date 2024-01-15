package com.example.EstatesFlow.Controllers.Project;

import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.DTO.Project.ProjectDTO;
import com.example.EstatesFlow.Services.Project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return projectService.getById(id);
    }



    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(long pageNumber){
        return projectService.getAll(pageNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable long id, @RequestBody Project project){
        project.setId(id);
        return projectService.updateProject(id ,project);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable long id){
        return projectService.deleteById(id);
    }
}

