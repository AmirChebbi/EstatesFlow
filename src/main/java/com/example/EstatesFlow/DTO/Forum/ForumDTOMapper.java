package com.example.EstatesFlow.DTO.Forum;

import com.example.EstatesFlow.Entities.Forum.Forum;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ForumDTOMapper implements Function<Forum, ForumDTO> {
    @Override
    public ForumDTO apply(Forum forum) {
        return new ForumDTO(
                forum.getUser().getFirstName() +" "+ forum.getUser().getLastName(),
                forum.getUser().getEmail(),
                forum.getUser().getPhoneNumber(),
                forum.getRelatedCompany().getCoName(),
                forum.getRelatedProject().getProjName(),
                forum.getWantedApartment().getApartmentNumber(),
                forum.getWantedApartment().getFloorNumber(),
                forum.getPrice()
        );
    }
}
