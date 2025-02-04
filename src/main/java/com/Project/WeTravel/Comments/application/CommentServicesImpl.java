/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Comments.application;

import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Comments.infrastructure.CommentJpaRepository;
import com.Project.WeTravel.Likes.application.DTO.LikeCommentDTO;
import com.Project.WeTravel.Likes.application.LikeServiceImpl;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Post.application.PostServiceImpl;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.application.UserServiceImpl;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Utilities.exceptions.InvalidInputException;
import com.Project.WeTravel.Utilities.exceptions.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServicesImpl implements CommentService {

    private final CommentJpaRepository commentJpaRepository;
    private final LikeServiceImpl likeServiceImpl;
    private final PostServiceImpl postServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public CommentServicesImpl(CommentJpaRepository commentJpaRepository, @Lazy LikeServiceImpl likeServiceImpl, PostServiceImpl postServiceImpl, UserServiceImpl userServiceImpl) {
        this.commentJpaRepository = commentJpaRepository;
        this.likeServiceImpl = likeServiceImpl;
        this.postServiceImpl = postServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    public ResponseEntity<Comment> findComentBYid(Long idComment) {

        Comment commentfound = commentJpaRepository.findById(idComment).get();

        return ResponseEntity.ok(commentfound);

    }

    @Override
    public List<CommentDTO> findAllByPost(Post post) {
        if (post == null) {
            throw new InvalidInputException("Post can not br null");
        }
        if (post.getIdPost() == null || post.getIdPost() <= 0) {
            throw new InvalidInputException("Id  Post is NOT  VALID ");
        }
        List<Comment> commentList = commentJpaRepository.findAllByPost(post);
        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment commentFound : commentList) {

            CommentDTO commentDTO = commentFound.toDTO();

            List<LikeCommentDTO> likeCommentDTOs = likeServiceImpl.findAllByComment(commentFound);

            commentDTO.setLikes(likeCommentDTOs);

            commentDTOList.add(commentDTO);
        }

        return commentDTOList;
    }

    @Override
    public Boolean hasPostcomment(Post post, Users user) {
        if (post == null || user == null) {
            throw new InvalidInputException("Post or user can not be null");
        }
        Optional<Comment> commentOPt = commentJpaRepository.findByPostAndUser(post, user);
        return commentOPt.isPresent();
    }

    public void savecomment(Comment comment ){
        commentJpaRepository.save(comment);
    }
    
    @Override
    public ResponseEntity<CommentDTO> createComment(Long idPost, Long idUser, Comment comment) {

        Users user = userServiceImpl.getUserNormalbyId(idUser);
        Post post = postServiceImpl.getPostsByPostid(idPost);
         if (post == null || user == null) {
            throw new InvalidInputException("Post or user can not be null");
        }
        if (hasPostcomment(post, user)) {
            return ResponseEntity.badRequest().build();
        }
        comment.setUser(user);
        comment.setPost(post);
        user.getCommentList().add(comment);
        post.getCommentList().add(comment);
        
        
        postServiceImpl.createPost(post);
        userServiceImpl.saveUserEntity(user);
        commentJpaRepository.save(comment);
        return ResponseEntity.ok(comment.toDTO());
    }

    @Override
    public void deleteComment(Long idcomment) {
         if (idcomment == null || idcomment <= 0) {
        throw new InvalidInputException("El ID del comentario no es válido");
    }
        Optional<Comment> commentYoEliminate = commentJpaRepository.findById(idcomment);
        if(!commentYoEliminate.isPresent()){
             throw new NotFoundException("Comment not found with ID: " + idcomment);
        }
        commentJpaRepository.delete(commentYoEliminate.get());

    }

}
