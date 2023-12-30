package com.example.EstatesFlow.Controllers;

import com.example.EstatesFlow.DAO.Entities.Forum;
import com.example.EstatesFlow.DAO.Entities.Project;
import com.example.EstatesFlow.DTO.ForumDTO;
import com.example.EstatesFlow.DTO.ProjectDTO;
import com.example.EstatesFlow.Services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/api/v1/forum")
public class ForumController {
    private final ForumService forumService;

    @Autowired
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @GetMapping("/getById/{id}")
    public ForumDTO getById(@PathVariable Long id){
        return forumService.getById(id);
    }


    @GetMapping("/getAll")
    public Vector<ForumDTO> getAll(){
        return forumService.getAll();
    }

    @PostMapping("/add")
    public Forum submitForum(@RequestBody Forum forum){
        return forumService.submitForum(forum);
    }

    @PutMapping("/update/{id}")
    public Forum updateForum(@PathVariable long id, @RequestBody Forum forum){
        forum.setId(id);
        return forumService.updateForum(forum, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteForum(@PathVariable long id){
        forumService.deleteForum(id);
    }


}

