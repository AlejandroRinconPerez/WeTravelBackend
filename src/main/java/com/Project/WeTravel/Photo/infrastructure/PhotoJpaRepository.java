package com.Project.WeTravel.Photo.infrastructure;

import com.Project.WeTravel.Photo.domain.Photo;
import com.Project.WeTravel.Post.domain.Post;
import java.util.List;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface PhotoJpaRepository extends JpaRepositoryImplementation<Photo, Long> {

 

    List<Photo> findAllByPost(Post post);
    
}
