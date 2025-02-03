package com.Project.WeTravel.Post.application;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Photo.domain.Photo;
import com.Project.WeTravel.Post.application.DTO.DTO.CreatePostDTO;
import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Tags.domain.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

public interface PostService {

   // Crear un nuevo post
    Post createPost(CreatePostDTO createPostDTO);

  

    // Eliminar un post dado su ID
    ResponseEntity<Void>  deletePost(Long idPost);

   

    // Obtener la lista de todos los posts
    List<ShowPostDTO> getAllPosts();

    // (Opcional) Obtener posts de un usuario específico
    List<ShowPostDTO> getPostsByUserId(Long idUser);

//    // (Opcional) Agregar un comentario a un post
//    Post addComment(Long postId, Comment comment);
//
//    // (Opcional) Agregar un like a un post
//    Post addLike(Long postId, Likes like);
//
//    // (Opcional) Agregar una foto a un post
//    Post addPhoto(Long postId, Photo photo);
//
//    // (Opcional) Agregar un tag a un post
//    Post addTag(Long postId, Tag tag);
//    

}
