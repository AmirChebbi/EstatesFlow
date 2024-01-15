package com.example.EstatesFlow.Controllers.Forum;

import com.example.EstatesFlow.Entities.Forum.Forum;
import com.example.EstatesFlow.DTO.Forum.ForumDTO;
import com.example.EstatesFlow.Services.Forum.ForumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
@RequestMapping("/api/v1/forum")
public class ForumController {
    private final ForumServiceImpl forumService;

    @Autowired
    public ForumController(ForumServiceImpl forumService) {
        this.forumService = forumService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return forumService.getById(id);
    }


    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(long pageNumber){
        return forumService.getAll(pageNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> submitForum(@RequestBody Forum forum){
        return forumService.submitForum(forum);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateForum(@PathVariable long id, @RequestBody Forum forum){
        return forumService.updateForum(forum, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteForum(@PathVariable long id){
        forumService.deleteForum(id);
    }


}

