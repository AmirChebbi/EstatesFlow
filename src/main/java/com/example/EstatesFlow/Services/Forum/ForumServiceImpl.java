package com.example.EstatesFlow.Services.Forum;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import com.example.EstatesFlow.DTO.Company.CompanyDTO;
import com.example.EstatesFlow.DTO.Forum.ForumDTO;
import com.example.EstatesFlow.DTO.Forum.ForumDTOMapper;
import com.example.EstatesFlow.DTO.Project.ProjectDTO;
import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Entities.Forum.Forum;
import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Entities.UserEntity.UserEntity;
import com.example.EstatesFlow.Exceptions.BadRequestException;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Repositories.Apartment.ApartmentRepository;
import com.example.EstatesFlow.Repositories.Company.CompanyRepository;
import com.example.EstatesFlow.Repositories.Forum.ForumRepository;
import com.example.EstatesFlow.Repositories.Project.ProjectRepository;
import com.example.EstatesFlow.Repositories.UserEntity.UserRepository;
import com.example.EstatesFlow.Utility.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber -1, 10);
        List<Forum> forums= forumRepository.findAllPaged(pageable);
        if (forums.isEmpty() && pageNumber >1 ){
            return getAll(1);
        }
        List<ForumDTO> forumDTOS =forums.stream().map(forumDTOMapper).toList();
        return ResponseHandler.generateResponse(forumDTOS, HttpStatus.OK, forumDTOS.size(), forumRepository.getCountPaged());
    }

    public ResponseEntity<Object> submitForum(ForumDTO forumDTO, UserDetails userDetails) {

        UserEntity user = userRepository.findByEmail(forumDTO.email()).orElseThrow(() -> new ResourceNotFoundException("We have trouble saving your forum !!"));
        if (Objects.equals(userDetails.getUsername(), user.getEmail())) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(forumDTO.email());
            simpleMailMessage.setSubject("Conformation de l'envoi de votre formulaire");
            simpleMailMessage.setText("just testing");

            SimpleMailMessage simpleMailMessageAdmin = new SimpleMailMessage();
            simpleMailMessageAdmin.setTo("amirchebbi60@gmail.com");
            simpleMailMessageAdmin.setSubject("You have a new potential client");
            simpleMailMessageAdmin.setText("Bro you have a new potential Client named : " + forumDTO.fullName() + ", their email is : " + forumDTO.email() + ", You better contact them, if you wanna make money of course." +
                    "all the other related info is in the forum");
            Apartment apartment = findApartmentByDTO(forumDTO.relatedApartment());
            Project project = findProjectByDTO(forumDTO.relatedProject());
            Company company = findCompanyByDTO(forumDTO.relatedCompany());

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
            throw new ResourceNotFoundException("We have trouble saving your forum !!");
        }
    }

    public ResponseEntity<Object> deleteForum(long id, UserDetails userDetails){
        Forum forum = forumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Forum doesn't exist !!"));
        if(Objects.equals(userDetails.getUsername(), forum.getUser().getEmail())){
            forumRepository.deleteById(id);
            String successMessage = String.format("Forum Deleted Successfully");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("This Forum Doesn't Exist !!");
        }
    }

    public ResponseEntity<Object> updateForum(ForumDTO forumDTO, UserDetails userDetails){
        if (!isForumValid(forumDTO,userDetails)) {
            Forum forum = forumRepository.findById(forumDTO.id()).orElseThrow(() -> new ResourceNotFoundException("Forum not found !!"));
            Apartment apartment = findApartmentByDTO(forumDTO.relatedApartment());
            Project project = findProjectByDTO(forumDTO.relatedProject());
            Company company = findCompanyByDTO(forumDTO.relatedCompany());

            forum.setWantedApartment(apartment);
            forum.setRelatedCompany(company);
            forum.setRelatedProject(project);
            forum.setPrice(forumDTO.price());

            forumRepository.save(forum);
            String successMessage = String.format("Forum Updated Successfully");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw  new BadRequestException("We have trouble saving your forum !!");
        }
    }

    private Apartment findApartmentByDTO(ApartmentDTO apartmentDTO){
        return apartmentRepository.findByDTO(apartmentDTO.apartmentDescription(), apartmentDTO.apartmentNumber() ,apartmentDTO.floorNumber()  ).orElseThrow(() -> new ResourceNotFoundException("Apartment doesn't exist !!"));
    }

    private Project findProjectByDTO(ProjectDTO projectDTO){
        return projectRepository.findByDTO(projectDTO.projName(), projectDTO.address(), projectDTO.projDescription()).orElseThrow(() -> new ResourceNotFoundException("Project doesn't exist !!"));
    }

    private Company findCompanyByDTO(CompanyDTO companyDTO){
        return companyRepository.findByDTO(companyDTO.coName(), companyDTO.coDescription()).orElseThrow(() -> new ResourceNotFoundException("Company doesn't exist !!"));

    }
    private boolean isForumValid(ForumDTO forumDTO, UserDetails userDetails){
        UserEntity user = userRepository.findByEmail(forumDTO.email()).orElseThrow(() -> new ResourceNotFoundException("User unknown !"));
        Apartment apartment = findApartmentByDTO(forumDTO.relatedApartment());
        Project project = findProjectByDTO(forumDTO.relatedProject());
        Company company = findCompanyByDTO(forumDTO.relatedCompany());
        if (!company.getProjects().contains(project) || !project.getApartments().contains(apartment) || !Objects.equals(user.getEmail(), userDetails.getUsername())){
            return false;
        } else {
            return true;
        }
    }

}
