package com.example.EstatesFlow.Controllers.Forum;

import com.example.EstatesFlow.DTO.Forum.ForumDTO;
import com.example.EstatesFlow.Services.Forum.ForumServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/forum")
public class ForumController {
    private final ForumServiceImpl forumService;

    public ForumController(ForumServiceImpl forumService) {
        this.forumService = forumService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return forumService.getById(id);
    }


    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(@RequestParam long pageNumber){
        return forumService.getAll(pageNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> submitForum(@RequestBody ForumDTO forumDTO, @AuthenticationPrincipal UserDetails userDetails){
        return forumService.submitForum(forumDTO,userDetails);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateForum(@RequestBody ForumDTO forumDTO, @AuthenticationPrincipal UserDetails userDetails){
        return forumService.updateForum(forumDTO, userDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteForum(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails){
        return forumService.deleteForum(id,userDetails);
    }


}

