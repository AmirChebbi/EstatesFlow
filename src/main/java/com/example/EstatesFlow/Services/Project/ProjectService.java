package com.example.EstatesFlow.Services.Project;

import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Repositories.Project.ProjectRepository;
import com.example.EstatesFlow.DTO.Project.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Vector;

@Service
public interface ProjectService {



    public ResponseEntity<Object> getById(@PathVariable Long id);

    public ResponseEntity<Object> getAll();


    public ResponseEntity<Object> getAllForSale();

    public ResponseEntity<Object> addProject(@RequestBody Project project);
    public ResponseEntity<Object> updateProject(@PathVariable long id, @RequestBody Project project);
    public ResponseEntity<Object> deleteById(@PathVariable long id) ;



}
