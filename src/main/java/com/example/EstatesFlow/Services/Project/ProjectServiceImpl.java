package com.example.EstatesFlow.Services.Project;

import com.example.EstatesFlow.DTO.Project.ProjectDTOMapper;
import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
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
    public ResponseEntity<Object> getById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("This Projects Doesn't Exist !!"));
        return ResponseHandler.generateResponse(projectDTOMapper.apply(project), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAll(long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber, 5);
        List<Project> projects = projectRepository.getAllPaged(pageable);
        return ResponseHandler.generateResponse(projects.stream().map(projectDTOMapper).toList(),HttpStatus.OK,projects.size(), projectRepository.getPagedCount(pageable));
    }


    @Override
    public ResponseEntity<Object> addProject(Project project) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateProject(long id, Project project) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteById(long id) {
        return null;
    }

    public static List<String> mapProjectsToNames(List<Project> projects){
        List<String> names =new ArrayList<>();
        for (Project project : projects){
            names.add(project.getProjName());
        }
        return names;
    }
}
