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
    public CommentServicesImpl(CommentJpaRepository commentJpaRepository, @Lazy  LikeServiceImpl likeServiceImpl, PostServiceImpl postServiceImpl, UserServiceImpl userServiceImpl) {
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

    public Boolean hasPostcomment(Post post, Users user){
        Optional<Comment> commentOPt =  commentJpaRepository.findByPostAndUser(post, user);
        return commentOPt.isPresent();
    }
    
    
    public ResponseEntity<CommentDTO> createComment(Long idPost, Long idUser, Comment comment  ) {

        Users user = userServiceImpl.getUserNormalbyId(idUser);
        Post post = postServiceImpl.getPostsByPostid(idPost);
        if(hasPostcomment(post, user)){
            return ResponseEntity.badRequest().build();
        }
        comment.setUser(user);
        comment.setPost(post);
        commentJpaRepository.save(comment);
        return ResponseEntity.ok(comment.toDTO());
    }

    
    public void deleteComment (Long idcomment){
        Optional<Comment> commentYoEliminate =  commentJpaRepository.findById(idcomment);
        
    }
    
}
