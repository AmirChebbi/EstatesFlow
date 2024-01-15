package com.example.EstatesFlow.Services.Forum;

import com.example.EstatesFlow.Entities.Forum.Forum;
import org.springframework.http.ResponseEntity;

public interface ForumService {
    public ResponseEntity<Object> getById( long id);

    public ResponseEntity<Object> getAll(long pageNumber);

    public ResponseEntity<Object> submitForum( Forum forum);


    public ResponseEntity<Object> deleteForum( long id);

    public ResponseEntity<Object> updateForum( Forum forum, long id);

}
