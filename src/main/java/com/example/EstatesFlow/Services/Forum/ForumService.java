package com.example.EstatesFlow.Services.Forum;

import com.example.EstatesFlow.DTO.Forum.ForumDTO;
import com.example.EstatesFlow.Entities.Forum.Forum;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface ForumService {
    public ResponseEntity<Object> getById( long id);

    public ResponseEntity<Object> getAll(long pageNumber);

    public ResponseEntity<Object> submitForum(ForumDTO forumDTO, UserDetails userDetails);

    public ResponseEntity<Object> deleteForum(long id, UserDetails userDetails);

    public ResponseEntity<Object> updateForum(ForumDTO forumDTO, UserDetails userDetails);

}
