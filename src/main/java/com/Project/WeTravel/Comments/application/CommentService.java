package com.Project.WeTravel.Comments.application;

import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    // Buscar todos los comentarios por post
    List<CommentDTO> findAllByPost(Post post);

    // Verificar si un post tiene comentario de un usuario específico
    Boolean hasPostcomment(Post post, Users user);

    // Crear un nuevo comentario
    ResponseEntity<CommentDTO> createComment(Long idPost, Comment comment, String email);

    // Eliminar un comentario dado su ID
    void deleteComment(Long idcomment);

    // Encontrar comentario por su ID
    ResponseEntity<Comment> findComentBYid(Long idComment);

    // Guardar un comentario
    ResponseEntity<Comment> saveComment(Comment comment);

    // Guardar un comentario (otra versión)
    void savecomment(Comment comment);

    // Buscar comentario por ID de like
    Comment findCommentByLike(Long idlike);

    // Actualizar un comentario
    ResponseEntity<Comment> updateComment(Long idComment, Comment updatedComment);
}
