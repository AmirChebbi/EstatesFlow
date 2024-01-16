package com.example.EstatesFlow.Controllers.Project;

import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.DTO.Project.ProjectDTO;
import com.example.EstatesFlow.Services.Project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
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
    public ResponseEntity<Object> getAll(@RequestParam long pageNumber){
        return projectService.getAll(pageNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addProject(@RequestBody ProjectDTO projectDTO){
        return projectService.addProject(projectDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProject(@RequestBody ProjectDTO projectDTO){
        return projectService.updateProject(projectDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable long id){
        return projectService.deleteById(id);
    }
}

