package com.example.EstatesFlow.Controllers.Project;

import com.example.EstatesFlow.DTO.Project.ProjectDTO;
import com.example.EstatesFlow.Services.Project.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

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
    public ResponseEntity<Object> addProject(@RequestBody ProjectDTO projectDTO, @RequestParam long companyId){
        return projectService.addProject(projectDTO, companyId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProject(@RequestBody ProjectDTO projectDTO){
        return projectService.updateProject(projectDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteById(@RequestParam long id, @RequestParam long companyId){
        return projectService.deleteById(id,companyId);
    }
}

