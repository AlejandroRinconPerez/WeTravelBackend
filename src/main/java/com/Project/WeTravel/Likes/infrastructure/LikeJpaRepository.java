
package com.Project.WeTravel.Likes.infrastructure;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Likes, Long>{
    
    List<Likes> findAllByPost(Post post);
    List<Likes> findAllByComment(Comment comment);
    Optional<Likes>  findByuser(Users user );
    Optional<Likes> findByPostAndUser(Post post, Users user);
    Optional<Likes> findByCommentAndUser(Comment comment, Users user);


    
}
