
package com.Project.WeTravel.Tags.infrastructure;

import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Tags.application.TagsDTO.TagDTO;
import com.Project.WeTravel.Tags.domain.Tag;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TagJpaRepository extends JpaRepository<Tag, Long>{
    
     Optional<Tag> findBytagContent(String name);
     boolean existsBytagContent(String tagContent); 
    
    
       @Query("SELECT new com.Project.WeTravel.Tags.application.TagsDTO.TagDTO(t.tagContent, COUNT(p)) " +
           "FROM Tag t JOIN t.postList p " +
           "GROUP BY t.tagContent")
    List<TagDTO> findTagUsageCount();
    
    
    List<Tag> findAllByPostList(Post post);

     
     
     
}
