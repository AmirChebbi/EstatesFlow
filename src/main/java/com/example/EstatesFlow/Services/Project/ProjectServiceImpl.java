package com.example.EstatesFlow.Services.Project;

import com.example.EstatesFlow.Entities.Project.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Override
    public ResponseEntity<Object> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Object> getAllForSale() {
        return null;
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
