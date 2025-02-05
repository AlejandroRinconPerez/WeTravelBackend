package com.Project.WeTravel.Post.infrastructure;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Tags.domain.Tag;
import com.Project.WeTravel.Users.domain.Users;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    List<Post> findByuser(Users user);

    @Query("SELECT p FROM Post p WHERE :comment MEMBER OF p.commentList")
    Post findByCommentInList(@Param("comment") Comment comment);

    //------------------ CON FILTRO DE USUARIO ACTIVO ------------------
    // 1. Posts por usuario (activo) ordenados por fecha
    @Query("SELECT p FROM Post p WHERE p.user = :user AND p.user.active = true ORDER BY p.creationDate DESC")
    List<Post> findByUserOrderByCreationDateDesc(@Param("user") Users user);

// New query: Posts by variable tag content
    @Query("SELECT p FROM Post p "
            + "JOIN p.tagList t "
            + "JOIN Users u ON p.user = u "
            + "WHERE t.tagContent = :tagContent "
            + "AND u.active = true "
            + "ORDER BY p.creationDate DESC")
    List<Post> findPostsByTagContent(@Param("tagContent") String tagContent);

    // 4. Todos los posts (usuarios activos) ordenados por likes
    @Query("SELECT p FROM Post p LEFT JOIN p.likeList l WHERE p.user.active = true GROUP BY p.idPost ORDER BY COUNT(l) DESC")
    List<Post> findAllOrderByLikesDesc();

    @Query("SELECT p FROM Post p JOIN p.user u WHERE u.active = true")
    List<Post> findPostsByActiveUsers();

    @Query("SELECT p FROM Post p "
            + "JOIN p.user u "
            + "JOIN Follow f ON f.follower.id = u.id "
            + "WHERE f.followed.id = :userId "
            + "ORDER BY p.creationDate DESC")
    List<Post> findPostsByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Post p "
            + "JOIN p.user u "
            + "JOIN Likes l ON l.post.id = p.id "
            + "WHERE l.user.id = :userId "
            + "ORDER BY p.creationDate DESC")
    List<Post> findLikedPostsByUserId(@Param("userId") Long userId);

}
