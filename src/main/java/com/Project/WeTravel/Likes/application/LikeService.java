package com.Project.WeTravel.Likes.application;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.application.DTO.LikeCommentDTO;
import com.Project.WeTravel.Likes.application.DTO.LikePostDTO;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface LikeService {

    // Encontrar todos los likes por post
    List<LikePostDTO> findAllByPost(Post post);

    // Encontrar todos los likes por comentario
    List<LikeCommentDTO> findAllByComment(Comment comment);

    // Eliminar un like dado su ID
    void deleteLike(Long idLike);

    // Verificar si un usuario ha dado like a un post
    Boolean hasUserLikedPost(Post post, Users user);

    // Verificar si un usuario ha dado like a un comentario
    Boolean hasUserLikedComment(Comment comment, Users user);

    // Crear un like para un post
    ResponseEntity<LikePostDTO> createLikePost(Long idPost, String email);

    // Crear un like para un comentario
    ResponseEntity<LikeCommentDTO> createLikeComment(Long idComment, String email);
}
