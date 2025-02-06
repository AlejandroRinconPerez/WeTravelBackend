package com.Project.WeTravel.Photo.infrastructure;

import com.Project.WeTravel.Photo.domain.Photo;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

public interface PhotoJpaRepository extends JpaRepositoryImplementation<Photo, Long> {

 

    List<Photo> findAllByPost(Post post);
      @Query("SELECT p FROM Photo p JOIN p.post post JOIN post.user user WHERE user = :user")
    List<Photo> findAllByUser(@Param("user") Users user);
    
}
