package com.Project.WeTravel.Post.application;

import com.Project.WeTravel.CombinePost.CombinePostDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Photo.domain.Photo;
import com.Project.WeTravel.Post.application.DTO.DTO.CreatePostDTO;
import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Tags.domain.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface PostService {

    // Crear un nuevo post
    ResponseEntity<ShowPostDTO> createPost(CreatePostDTO createPostDTO, String email);

    // Eliminar un post dado su ID
    ResponseEntity<Void> deletePost(Long idPost);

    // Obtener la lista de todos los posts
    List<ShowPostDTO> getAllPosts();

    // Obtener posts de un usuario específico
    List<ShowPostDTO> getPostsByUserId(Long idUser);

    // Métodos adicionales
    List<CombinePostDTO> getAllPosts2(List<Post> postList);
    CombinePostDTO getSinglePostDetails(Post postItem);
    CombinePostDTO getPostById(Long idPost);
    ResponseEntity<Post> updatePost(Long postId, CreatePostDTO createPostDTO);
    ResponseEntity<Post> getPostByIdLike(Long idLike);
    List<CombinePostDTO> findByUserOrderByCreationDateDesc(Long idUser);
    List<CombinePostDTO> getAllPost();
    List<CombinePostDTO> findPostsByTagContent(String tagtext);
    List<CombinePostDTO> findAllOrderByLikesDesc();
    List<CombinePostDTO> findPostsByUserId(Long userid);
    List<CombinePostDTO> findPostsByActiveUsers();
    List<CombinePostDTO> findPostsLikedByUser(Long id);
    Post getPostbycomment(Comment comment);
}
