package com.example.EstatesFlow.DTO.Forum;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTOMapper;
import com.example.EstatesFlow.DTO.Company.CompanyDTOMapper;
import com.example.EstatesFlow.DTO.Project.ProjectDTOMapper;
import com.example.EstatesFlow.Entities.Forum.Forum;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ForumDTOMapper implements Function<Forum, ForumDTO> {
    private final CompanyDTOMapper companyDTOMapper;
    private final ApartmentDTOMapper apartmentDTOMapper;
    private final ProjectDTOMapper projectDTOMapper;

    public ForumDTOMapper(CompanyDTOMapper companyDTOMapper, ApartmentDTOMapper apartmentDTOMapper, ProjectDTOMapper projectDTOMapper) {
        this.companyDTOMapper = companyDTOMapper;
        this.apartmentDTOMapper = apartmentDTOMapper;
        this.projectDTOMapper = projectDTOMapper;
    }

    @Override
    public ForumDTO apply(Forum forum) {
        return new ForumDTO(
                forum.getId(),
                forum.getUser().getFirstName() +" "+ forum.getUser().getLastName(),
                forum.getUser().getEmail(),
                forum.getUser().getPhoneNumber(),
                companyDTOMapper.apply(forum.getRelatedCompany()),
                projectDTOMapper.apply(forum.getRelatedProject()),
                apartmentDTOMapper.apply(forum.getWantedApartment()),
                forum.getPrice()
        );
    }
}
