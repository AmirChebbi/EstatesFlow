package com.example.EstatesFlow.Services.Project;

import com.example.EstatesFlow.DTO.Project.ProjectDTO;
import com.example.EstatesFlow.DTO.Project.ProjectDTOMapper;
import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Exceptions.UnauthorizedActionException;
import com.example.EstatesFlow.Repositories.Company.CompanyRepository;
import com.example.EstatesFlow.Repositories.Project.ProjectRepository;
import com.example.EstatesFlow.Utility.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;
    private final ProjectDTOMapper projectDTOMapper;

    public ProjectServiceImpl(CompanyRepository companyRepository, ProjectRepository projectRepository, ProjectDTOMapper projectDTOMapper) {
        this.companyRepository = companyRepository;
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
        return ResponseHandler.generateResponse(projects.stream().map(projectDTOMapper).toList(),HttpStatus.OK,projects.size(), projectRepository.getPagedCount());
    }


    @Override
    public ResponseEntity<Object> addProject(ProjectDTO projectDTO,long companyId) {
        if (projectRepository.findByDTO(projectDTO.projName(),projectDTO.address(),projectDTO.projDescription()).isEmpty()){
            Project project = projectRepository.save(new Project(
                    projectDTO.projName(),
                    projectDTO.projDescription(),
                    projectDTO.address(),
                    projectDTO.projImageURL()
            ));
            addProjectToCompany(project,companyId);
            String successMessage = String.format("Project added successfully !!");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new UnauthorizedActionException("Project already Exists !!");
        }
    }

    @Override
    public ResponseEntity<Object> updateProject(ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectDTO.id()).orElseThrow(() -> new ResourceNotFoundException("Project doesn't exists !!"));
        project.setProjName(projectDTO.projName());
        project.setProjDescription(project.getProjDescription());
        project.setAddress(projectDTO.address());
        projectRepository.save(project);
        String successMessage = String.format("Project saved successfully");
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteById(long id, long companyId) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Project doesn't exist !!"));
        deleteProjectFromCompany(project, companyId);
        projectRepository.deleteById(id);
        String successMessage = String.format("Project deleted successfully");
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);

    }

    private void addProjectToCompany(Project project, long companyId){
        Company company = companyRepository.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company doesn't exist !!"));
        List<Project> projects = company.getProjects();
        if (!projects.contains(project)) {
            projects.add(project);
            company.setProjects(projects);
            companyRepository.save(company);
        } else {
            throw new UnauthorizedActionException("Company already has this project !!");
        }
    }
    private void deleteProjectFromCompany(Project project, long companyId){
        Company company = companyRepository.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company doesn't exist !!"));
        List<Project> projects = company.getProjects();
        if (projects.contains(project)) {
            projects.remove(project);
            company.setProjects(projects);
            companyRepository.save(company);
        } else {
            throw new UnauthorizedActionException("Company already has this project !!");
        }
    }
}
