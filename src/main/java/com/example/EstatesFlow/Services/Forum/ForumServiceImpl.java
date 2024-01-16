package com.example.EstatesFlow.Services.Forum;

import com.example.EstatesFlow.DTO.Forum.ForumDTOMapper;
import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Entities.Forum.Forum;
import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Entities.UserEntity.UserEntity;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Repositories.Apartment.ApartmentRepository;
import com.example.EstatesFlow.Repositories.Company.CompanyRepository;
import com.example.EstatesFlow.Repositories.Forum.ForumRepository;
import com.example.EstatesFlow.DTO.Forum.ForumDTO;
import com.example.EstatesFlow.Repositories.Project.ProjectRepository;
import com.example.EstatesFlow.Repositories.UserEntity.UserRepository;
import com.example.EstatesFlow.Utility.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;

@Service
public class ForumServiceImpl implements ForumService{
    private final ForumRepository forumRepository;
    private final JavaMailSender javaMailSender;
    private final ForumDTOMapper forumDTOMapper;

    private final ApartmentRepository apartmentRepository;
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;



    public ForumServiceImpl(ForumRepository forumRepository, JavaMailSender javaMailSender, ForumDTOMapper forumDTOMapper, ApartmentRepository apartmentRepository, ProjectRepository projectRepository, CompanyRepository companyRepository, UserRepository userRepository) {
        this.forumRepository = forumRepository;
        this.javaMailSender = javaMailSender;
        this.forumDTOMapper = forumDTOMapper;
        this.apartmentRepository = apartmentRepository;
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> getById(long id){
        Forum forum = forumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This forum doesn't Exist !!"));
        return ResponseHandler.generateResponse(forumDTOMapper.apply(forum), HttpStatus.OK);
    }

    public ResponseEntity<Object> getAll(long pageNumber){
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber, 10);
        List<Forum> forums= forumRepository.findAllPaged(pageable);
        if (forums.isEmpty() && pageNumber >1 ){
            return getAll(1);
        }
        List<ForumDTO> forumDTOS =forums.stream().map(forumDTOMapper).toList();
        return ResponseHandler.generateResponse(forumDTOS, HttpStatus.OK, forumDTOS.size(), forumRepository.getCountPaged(pageable));
    }

    public ResponseEntity<Object> submitForum(ForumDTO forumDTO, UserDetails userDetails) {
        UserEntity user = userRepository.findByEmail(forumDTO.email()).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
        if (Objects.equals((UserEntity) userDetails, user)) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(forumDTO.email());
            simpleMailMessage.setSubject("Conformation de l'envoi de votre formulaire");
            simpleMailMessage.setText("");

            SimpleMailMessage simpleMailMessageAdmin = new SimpleMailMessage();
            simpleMailMessageAdmin.setTo("");
            simpleMailMessageAdmin.setSubject("You have a new potential client");
            simpleMailMessageAdmin.setText("Bro you have a new potential Client named : " + forumDTO.fullName() + ", their email is : " + forumDTO.email() + ", You better contact them, if you wanna make money of course." +
                    "all the other related info is in the forum");
            Apartment apartment = apartmentRepository.findByDTO(forumDTO.relatedApartment().apartmentNumber(), forumDTO.relatedApartment().floorNumber(), forumDTO.relatedApartment().apartmentDescription()).orElseThrow(() -> new ResourceNotFoundException("Apartment doesn't exist !!"));
            Project project = projectRepository.findByDTO(forumDTO.relatedProject().projName(), forumDTO.relatedProject().address(), forumDTO.relatedProject().projDescription()).orElseThrow(() -> new ResourceNotFoundException("Project doesn't exist !!"));
            Company company = companyRepository.findByDTO(forumDTO.relatedCompany().coName(), forumDTO.relatedCompany().coDescription()).orElseThrow(() -> new ResourceNotFoundException("Company doesn't exist !!"));

            forumRepository.save(new Forum(
                    user,
                    company,
                    project,
                    apartment,
                    forumDTO.price()
            ));
            javaMailSender.send(simpleMailMessageAdmin, simpleMailMessage);
            String successMessage = String.format("Your Forum was sent successfully, a confirmation was sent to your email, Thank you for choosing to work with us");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("User doesn't exist");
        }
    }

    public ResponseEntity<Object> deleteForum(long id){
        if(forumRepository.existsById(id)){
            forumRepository.deleteById(id);
            String successMessage = String.format("Forum Deleted Successfully");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("This Forum Doesn't Exist !!");
        }
    }

    public ResponseEntity<Object> updateForum(Forum forum, long id){
        if (forumRepository.existsById(id)) {
            forum.setId(id);
            forumRepository.save(forum);
            String successMessage = String.format("Forum Updated Successfully");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("This Forum Doesn't Exist");
        }
    }

}
