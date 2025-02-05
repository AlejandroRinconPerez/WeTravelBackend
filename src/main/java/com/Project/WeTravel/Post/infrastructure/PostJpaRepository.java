

package com.Project.WeTravel.Post.infrastructure;

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


public interface PostJpaRepository extends JpaRepository<Post, Long>{
    
 List<Post> findByuser(Users user);

 
 


    //------------------ CON FILTRO DE USUARIO ACTIVO ------------------
    
    // 1. Posts por usuario (activo) ordenados por fecha
    @Query("SELECT p FROM Post p WHERE p.user = :user AND p.user.active = true ORDER BY p.creationDate DESC")
    List<Post> findByUserOrderByCreationDateDesc(@Param("user") Users user);
    
  


    // 2. Posts por tag (usuario activo) ordenados por fecha
    @Query("SELECT p FROM Post p JOIN p.tagList t WHERE t = :tag AND p.user.active = true ORDER BY p.creationDate DESC")
    List<Post> findByTagOrderByCreationDateDesc(@Param("tag") Tag tag);

    // 3. Posts en rango de fechas (usuario activo) ordenados por fecha
    @Query("SELECT p FROM Post p WHERE p.creationDate BETWEEN :startDate AND :endDate AND p.user.active = true ORDER BY p.creationDate DESC")
    List<Post> findByCreationDateBetweenOrderByCreationDateDesc(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // 4. Todos los posts (usuarios activos) ordenados por likes
    @Query("SELECT p FROM Post p LEFT JOIN p.likeList l WHERE p.user.active = true GROUP BY p.idPost ORDER BY COUNT(l) DESC")
    List<Post> findAllOrderByLikesDesc();

    // 5. Posts por usuario y rango de fechas (activo) ordenados por fecha // Pendeinte 
    @Query("SELECT p FROM Post p WHERE p.user = :user AND p.creationDate BETWEEN :startDate AND :endDate AND p.user.active = true ORDER BY p.creationDate DESC")
    List<Post> findByUserAndCreationDateBetweenOrderByCreationDateDesc(@Param("user") Users user, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    //------------------ FILTRO ADICIONAL: POSTS DE USUARIOS SEGUIDOS (Y ACTIVOS) ------------------
    
   @Query("SELECT p FROM Post p " +
           "WHERE p.user.active = true " +
           "AND p.user IN (SELECT f.followed FROM Follow f WHERE f.follower = :currentUser) " +
           "ORDER BY p.creationDate DESC")
    List<Post> findFollowedUsersPostsOrderByCreationDateDesc(@Param("currentUser") Users currentUser);

    // 2. Posts de usuarios seguidos por tag - ordenados por fecha
    @Query("SELECT p FROM Post p " +
           "JOIN p.tagList t " +
           "WHERE t = :tag " +
           "AND p.user.active = true " +
           "AND p.user IN (SELECT f.followed FROM Follow f WHERE f.follower = :currentUser) " +
           "ORDER BY p.creationDate DESC")
    List<Post> findFollowedUsersPostsByTagOrderByCreationDateDesc(
        @Param("tag") Tag tag, 
        @Param("currentUser") Users currentUser
    );

    // 3. Posts de usuarios seguidos en rango de fechas
    @Query("SELECT p FROM Post p " +
           "WHERE p.creationDate BETWEEN :startDate AND :endDate " +
           "AND p.user.active = true " +
           "AND p.user IN (SELECT f.followed FROM Follow f WHERE f.follower = :currentUser) " +
           "ORDER BY p.creationDate DESC")
    List<Post> findFollowedUsersPostsByDateRangeOrderByCreationDateDesc(
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("currentUser") Users currentUser
    );

    // 4. Posts de usuarios seguidos ordenados por likes
    @Query("SELECT p FROM Post p " +
           "LEFT JOIN p.likeList l " +
           "WHERE p.user.active = true " +
           "AND p.user IN (SELECT f.followed FROM Follow f WHERE f.follower = :currentUser) " +
           "GROUP BY p.idPost " +
           "ORDER BY COUNT(l) DESC")
    List<Post> findFollowedUsersPostsOrderByLikesDesc(@Param("currentUser") Users currentUser);

    @Query("SELECT p FROM Post p WHERE p.user.active = true ORDER BY p.creationDate DESC")
List<Post> findAllActiveUsersPostsOrderByCreationDateDesc();




// New query: Posts by variable tag content
    @Query("SELECT p FROM Post p " +
           "JOIN p.tagList t " +
           "JOIN Users u ON p.user = u " +
           "WHERE t.tagContent = :tagContent " +
           "AND u.active = true " +
           "ORDER BY p.creationDate DESC")
    List<Post> findPostsByTagContent(@Param("tagContent") String tagContent);
}
