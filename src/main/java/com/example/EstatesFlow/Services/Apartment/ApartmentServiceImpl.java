package com.example.EstatesFlow.Services.Apartment;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import com.example.EstatesFlow.DTO.Apartment.ApartmentDTOMapper;
import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Exceptions.UnauthorizedActionException;
import com.example.EstatesFlow.Repositories.Apartment.ApartmentRepository;
import com.example.EstatesFlow.Repositories.Project.ProjectRepository;
import com.example.EstatesFlow.Utility.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApartmentServiceImpl implements ApartmentService{

    private final ApartmentRepository apartmentRepository;

    private final ApartmentDTOMapper apartmentDTOMapper;
    private final ProjectRepository projectRepository;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, ApartmentDTOMapper apartmentDTOMapper, ProjectRepository projectRepository) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentDTOMapper = apartmentDTOMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public ResponseEntity<Object> getById(long id) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This apartment Doesn't exist in our system !!"));
        return ResponseHandler.generateResponse(apartment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllApartmentsForSale(long id, long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber, 5);
        List<Apartment> apartments = apartmentRepository.getOneProjectApartmentsForSalePaged(id, false, pageable);
        if(apartments.isEmpty() && pageNumber > 1)
        {
            return getAllApartmentsForSale(id,1);
        }
        return ResponseHandler.generateResponse(apartments.stream().map(apartmentDTOMapper).toList(),HttpStatus.OK,apartments.size(), apartmentRepository.getOneProjectApartmentsPagedCount(id, false));
    }

    @Override
    public ResponseEntity<Object> getAll(long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber, 5);
        List<Apartment> apartments = apartmentRepository.getAllPaged(pageable);
        if(apartments.isEmpty() && pageNumber > 1)
        {
            return getAll(1);
        }
        return ResponseHandler.generateResponse(apartments.stream().map(apartmentDTOMapper).toList(),HttpStatus.OK,apartments.size(), apartmentRepository.getPagedCount());
    }

    @Override
    public ResponseEntity<Object> addApartment(ApartmentDTO apartmentDTO, long projectId) {
        if (apartmentRepository.findByDTO(apartmentDTO.apartmentDescription(), apartmentDTO.apartmentNumber(),apartmentDTO.floorNumber()).isEmpty()){
            Apartment apartment = apartmentRepository.save(
                    new Apartment(
                            apartmentDTO.apartmentNumber(),
                            apartmentDTO.floorNumber(),
                            apartmentDTO.apartmentDescription()
                    )
            );
            addApartmentTOProject(apartment,projectId);
            String successMessage = String.format("Apartment saved successfully !!");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new UnauthorizedActionException("This Apartment already exists !!");
        }
    }

    @Override
    public ResponseEntity<Object> updateApartment(long id,ApartmentDTO apartmentDTO) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("This Apartment doesn't exist !!"));
        apartment.setSold(apartmentDTO.sold());
        apartment.setApartmentNumber(apartmentDTO.apartmentNumber());
        apartment.setApartmentDescription(apartmentDTO.apartmentDescription());
        apartment.setFloorNumber(apartmentDTO.floorNumber());
        apartmentRepository.save(apartment);
        String successMessage= String.format("Updates saved Successfully !!");
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteById(long id, long projectId) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Apartment doesn't exist !!"));
        deleteApartmentFromProject(apartment,projectId);
        apartmentRepository.deleteById(id);
        String successMessage = String.format("Apartment deleted successfully !!");
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }

    private void addApartmentTOProject(Apartment apartment, long companyId){
        Project project = projectRepository.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Project doesn't exist !!"));
        List<Apartment> apartments = project.getApartments();
        if (!apartments.contains(apartment)) {
            apartments.add(apartment);
            project.setApartments(apartments);
            projectRepository.save(project);
        } else {
            throw new UnauthorizedActionException("Project already has this project !!");
        }
    }
    private void deleteApartmentFromProject(Apartment apartment, long companyId){
        Project project = projectRepository.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Project doesn't exist !!"));
        List<Apartment> apartments = project.getApartments();
        if (apartments.contains(apartment)) {
            apartments.remove(apartment);
            project.setApartments(apartments);
            projectRepository.save(project);
        } else {
            throw new UnauthorizedActionException("Project doesn't have this project !!");
        }
    }
}
