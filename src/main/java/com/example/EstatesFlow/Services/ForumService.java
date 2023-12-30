package com.example.EstatesFlow.Services;

import com.example.EstatesFlow.DAO.Entities.Forum;
import com.example.EstatesFlow.DAO.Repositories.ForumRepository;
import com.example.EstatesFlow.DTO.ForumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Vector;

@Service
public class ForumService {

    private final ForumRepository forumRepository;

    private final JavaMailSender javaMailSender;

    public ForumService(ForumRepository forumRepository, JavaMailSender javaMailSender) {
        this.forumRepository = forumRepository;
        this.javaMailSender = javaMailSender;
    }

    public ForumDTO getById(@PathVariable long id){
        return new ForumDTO(forumRepository.getById(id));
    }

    public Vector<ForumDTO> getAll(){
        List<Forum> forums= forumRepository.findAll();
        Vector<ForumDTO> forumDTOS = new Vector<>();
        for (Forum forum : forums){
            forumDTOS.add(new ForumDTO(forum));
        }
        return forumDTOS;
    }

    public Forum submitForum(@RequestBody Forum forum){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(forum.getEmail());
        simpleMailMessage.setSubject("Conformation de l'envoi de votre formulaire");
        simpleMailMessage.setText("");
        javaMailSender.send(simpleMailMessage);
        SimpleMailMessage simpleMailMessageAdmin = new SimpleMailMessage();
        simpleMailMessageAdmin.setTo("");
        simpleMailMessageAdmin.setSubject("You have a new potential client");
        simpleMailMessageAdmin.setText("Bro you have a new potential Client named : " + forum.getFirstName() + forum.getLastName() + ", their email is : " + forum.getEmail() + ", You better contact them, if you wanna make money of course." +
                "all the other related info is in the forum number " + forum.getId());
        return forumRepository.save(forum);
    }

    public void deleteForum(@PathVariable long id){
        forumRepository.deleteById(id);
    }

    public Forum updateForum(@RequestBody Forum forum, @PathVariable long id){
        forum.setId(id);
        return forumRepository.save(forum);
    }


}
