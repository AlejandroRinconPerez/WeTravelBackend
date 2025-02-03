
package com.Project.WeTravel.Comments.application;

import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import org.springframework.http.ResponseEntity;


public interface CommentService {
    
    
     List<CommentDTO> findAllByPost(Post post);
     public Boolean hasPostcomment(Post post, Users user);
     public ResponseEntity<CommentDTO> createComment(Long idPost, Long idUser, Comment comment);
     public void deleteComment(Long idcomment);
}
