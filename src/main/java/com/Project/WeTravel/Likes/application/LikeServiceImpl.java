package com.Project.WeTravel.Likes.application;

import com.Project.WeTravel.Comments.application.CommentServicesImpl;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.application.DTO.LikeCommentDTO;
import com.Project.WeTravel.Likes.application.DTO.LikePostDTO;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Likes.infrastructure.LikeJpaRepository;
import com.Project.WeTravel.Post.application.PostServiceImpl;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.application.UserServiceImpl;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Utilities.exceptions.InvalidInputException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.RequestEntity.post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeJpaRepository likeJpaRepository;
    private final PostServiceImpl postServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final CommentServicesImpl commentServicesImpl;

    @Autowired
    public LikeServiceImpl(LikeJpaRepository likeJpaRepository, PostServiceImpl postServiceImpl, UserServiceImpl userServiceImpl, CommentServicesImpl commentServicesImpl) {
        this.likeJpaRepository = likeJpaRepository;
        this.postServiceImpl = postServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.commentServicesImpl = commentServicesImpl;
    }

    @Override
    public List<LikePostDTO> findAllByPost(Post post) {

        List<Likes> likesList = likeJpaRepository.findAllByPost(post);
        return likesList.stream()
                .map(Likes::toLikePostDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<LikeCommentDTO> findAllByComment(Comment comment) {

        List<Likes> likesList = likeJpaRepository.findAllByComment(comment);
        return likesList.stream()
                .map(Likes::toLikeCommentDTO)
                .collect(Collectors.toList());

    }

    // to Delete a like 
    @Override
    public void deleteLike(Long idLike) {
        Optional<Likes> likeToEliminate = likeJpaRepository.findById(idLike);

        if (likeToEliminate.isEmpty()) {
            throw new InvalidInputException("Like not found with id: " + idLike);
        }

        likeJpaRepository.delete(likeToEliminate.get());
    }

    @Override
    public Boolean hasUserLikedPost(Post post, Users user) {
        Optional<Likes> like = likeJpaRepository.findByPostAndUser(post, user);
        return like.isPresent();
    }

    @Override
    public Boolean hasUserLikedComment(Comment comment, Users user) {
        Optional<Likes> like = likeJpaRepository.findByCommentAndUser(comment, user);
        return like.isPresent();
    }

    //To create a like for a Post 
    @Override
    public ResponseEntity<LikePostDTO> createLikePost(Long idPost, Long iduser) {
      
        Post postlike = postServiceImpl.getPostsByPostid(idPost);
        Users user = userServiceImpl.getUserNormalbyId(iduser);
        
          if (hasUserLikedPost(postlike, user)) {
            return ResponseEntity.badRequest().build();
        }
        Likes like = new Likes(user, postlike);
        likeJpaRepository.save(like);
        return ResponseEntity.ok(like.toLikePostDTO());

    }

    
    
    
    // To create  a like for a comment 
    @Override
    public ResponseEntity<LikeCommentDTO> createLikeComment(Long idComment, Long iduser) {
      
        Comment commentlike = commentServicesImpl.findComentBYid(idComment).getBody();
        Users user = userServiceImpl.getUserNormalbyId(iduser);
        if (hasUserLikedComment(commentlike, user)) {
            return ResponseEntity.badRequest().build();
        }
        Likes like = new Likes(user, commentlike);
          
        likeJpaRepository.save(like);
        return ResponseEntity.ok(like.toLikeCommentDTO());

    }

}
