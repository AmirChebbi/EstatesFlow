package com.example.EstatesFlow.Services.Project;

import com.example.EstatesFlow.DTO.Project.ProjectDTO;
import com.example.EstatesFlow.DTO.Project.ProjectDTOMapper;
import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Exceptions.UnauthorizedActionException;
import com.example.EstatesFlow.Repositories.Project.ProjectRepository;
import com.example.EstatesFlow.Utility.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectDTOMapper projectDTOMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectDTOMapper projectDTOMapper) {
        this.projectRepository = projectRepository;
        this.projectDTOMapper = projectDTOMapper;
    }

    @Override
    public ResponseEntity<Object> getById(long id) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("This Projects Doesn't Exist !!"));
        return ResponseHandler.generateResponse(projectDTOMapper.apply(project), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAll(long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber, 5);
        List<Project> projects = projectRepository.getAllPaged(pageable);
        if (projects.isEmpty() && pageNumber>1){
            return getAll(1);
        }
        return ResponseHandler.generateResponse(projects.stream().map(projectDTOMapper).toList(),HttpStatus.OK,projects.size(), projectRepository.getPagedCount(pageable));
    }


    @Override
    public ResponseEntity<Object> addProject(ProjectDTO projectDTO) {
        if (projectRepository.findByDTO(projectDTO.projName(),projectDTO.address(),projectDTO.projDescription()).isEmpty()){
            projectRepository.save(new Project(
                    projectDTO.projName(),
                    projectDTO.projDescription(),
                    projectDTO.address(),
                    projectDTO.projImageURL()
            ));
            String successMessage = String.format("Project added successfully !!");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new UnauthorizedActionException("Project already Exists !!");
        }
    }

    @Override
    public ResponseEntity<Object> updateProject(long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project doesn't exists !!"));
        project.setProjName(projectDTO.projName());
        project.setProjDescription(project.getProjDescription());
        project.setAddress(projectDTO.address());
        projectRepository.save(project);
        String successMessage = String.format("Project saved successfully");
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteById(long id) {
        if (!projectRepository.findById(id).isEmpty()) {
            projectRepository.deleteById(id);
            String successMessage = String.format("Project deleted successfully");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new UnauthorizedActionException("Project doesn't exist !!");
        }
    }

    public static List<String> mapProjectsToNames(List<ProjectDTO> projects){
        List<String> names =new ArrayList<>();
        for (ProjectDTO project : projects){
            names.add(project.projName());
        }
        return names;
    }
}
