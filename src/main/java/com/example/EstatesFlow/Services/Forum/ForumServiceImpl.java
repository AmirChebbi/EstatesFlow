package com.example.EstatesFlow.Services.Forum;

import com.example.EstatesFlow.DTO.Forum.ForumDTOMapper;
import com.example.EstatesFlow.Entities.Forum.Forum;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Repositories.Forum.ForumRepository;
import com.example.EstatesFlow.DTO.Forum.ForumDTO;
import com.example.EstatesFlow.Utility.ResponseHandler;
import org.hibernate.query.hql.HqlLogging_$logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
public class ForumServiceImpl implements ForumService{

    private final ForumRepository forumRepository;

    private final JavaMailSender javaMailSender;

    private final ForumDTOMapper forumDTOMapper;

    public ForumServiceImpl(ForumRepository forumRepository, JavaMailSender javaMailSender, ForumDTOMapper forumDTOMapper) {
        this.forumRepository = forumRepository;
        this.javaMailSender = javaMailSender;
        this.forumDTOMapper = forumDTOMapper;
    }

    public ResponseEntity<Object> getById(long id){
        Forum forum = forumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This forum doesn't Exist !!"));
        return ResponseHandler.generateResponse(forumDTOMapper.apply(forum), HttpStatus.OK);
    }

    public ResponseEntity<Object> getAll(){
        List<Forum> forums= forumRepository.findAll();
        List<ForumDTO> forumDTOS =forums.stream().map(forumDTOMapper).toList();
        return ResponseHandler.generateResponse(forumDTOS, HttpStatus.OK);
    }

    public ResponseEntity<Object> submitForum(Forum forum){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(forum.getUser().getEmail());
        simpleMailMessage.setSubject("Conformation de l'envoi de votre formulaire");
        simpleMailMessage.setText("");

        SimpleMailMessage simpleMailMessageAdmin = new SimpleMailMessage();
        simpleMailMessageAdmin.setTo("");
        simpleMailMessageAdmin.setSubject("You have a new potential client");
        simpleMailMessageAdmin.setText("Bro you have a new potential Client named : " + forum.getUser().getFirstName() + forum.getUser().getLastName() + ", their email is : " + forum.getUser().getEmail() + ", You better contact them, if you wanna make money of course." +
                "all the other related info is in the forum number " + forum.getId());

        forumRepository.save(forum);
        javaMailSender.send(simpleMailMessageAdmin,simpleMailMessage);
        String successMessage= String.format("Your Forum was sent successfully, a confirmation was sent to your email, Thank you for choosing to work with us");
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
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
