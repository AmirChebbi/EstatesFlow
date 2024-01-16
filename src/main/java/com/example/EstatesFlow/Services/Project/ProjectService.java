package com.example.EstatesFlow.Services.Project;

import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Repositories.Project.ProjectRepository;
import com.example.EstatesFlow.DTO.Project.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Vector;


public interface ProjectService {

    public ResponseEntity<Object> getById(long id);
    public ResponseEntity<Object> getAll(long pageNumber);

    public ResponseEntity<Object> addProject(ProjectDTO projectDTO, long companyId);
    public ResponseEntity<Object> updateProject(ProjectDTO projectDTO);
    public ResponseEntity<Object> deleteById(long id, long companyId) ;



}
