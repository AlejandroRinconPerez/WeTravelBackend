/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Comments.infrastructure;

import com.Project.WeTravel.Comments.domain.Comment;

import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);
    Optional<Comment> findByPostAndUser(Post post, Users user);

}
