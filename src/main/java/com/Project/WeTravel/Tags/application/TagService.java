package com.Project.WeTravel.Tags.application;

import com.Project.WeTravel.Tags.application.TagsDTO.TagDTO;
import com.Project.WeTravel.Tags.domain.Tag;
import com.Project.WeTravel.Users.application.UserDTO.CreateUserDTO;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

public interface TagService {

    List<TagDTO> getAllTagsDTO();
    List<Tag> getAllTags();
    Optional<Tag> findBytagContent(String tag);
    ResponseEntity<Tag> createTag(Tag tag);
    ResponseEntity<Void>deleteTag(Long tag);
    Boolean verificarTag(String tag );
    


}
