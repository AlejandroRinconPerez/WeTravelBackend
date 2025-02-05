package com.Project.WeTravel.Comments.application;

import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    List<CommentDTO> findAllByPost(Post post);

    Boolean hasPostcomment(Post post, Users user);

    ResponseEntity<CommentDTO> createComment(Long idPost, Comment comment, String email);

    void deleteComment(Long idcomment);

    ResponseEntity<Comment> findComentBYid(Long idComment);

    ResponseEntity<Comment> saveComment(Comment comment);

    void savecomment(Comment comment);
}
