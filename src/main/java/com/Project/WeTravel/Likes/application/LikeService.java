package com.Project.WeTravel.Likes.application;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.application.DTO.LikeCommentDTO;
import com.Project.WeTravel.Likes.application.DTO.LikePostDTO;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface LikeService {

    List<LikePostDTO> findAllByPost(Post post);

    List<LikeCommentDTO> findAllByComment(Comment comment);

    void deleteLike(Long idLike);

    Boolean hasUserLikedPost(Post post, Users user);

    Boolean hasUserLikedComment(Comment comment, Users user);

    ResponseEntity<LikePostDTO> createLikePost(Long idPost, Long idUser);

    ResponseEntity<LikeCommentDTO> createLikeComment(Long idComment, Long idUser);
}
