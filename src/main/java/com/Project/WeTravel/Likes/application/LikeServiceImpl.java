package com.Project.WeTravel.Likes.application;

import com.Project.WeTravel.Comments.application.CommentServicesImpl;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.application.DTO.LikeCommentDTO;
import com.Project.WeTravel.Likes.application.DTO.LikePostDTO;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Likes.infrastructure.LikeJpaRepository;
import com.Project.WeTravel.Notification.application.NotificationServiceImp;
import com.Project.WeTravel.Post.application.PostServiceImpl;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.application.UserServiceImpl;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Utilities.exceptions.InvalidInputException;
import com.Project.WeTravel.Utilities.exceptions.NotFoundException;
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
    private final NotificationServiceImp notificationServiceImp;

    @Autowired
    public LikeServiceImpl(LikeJpaRepository likeJpaRepository, PostServiceImpl postServiceImpl, UserServiceImpl userServiceImpl, CommentServicesImpl commentServicesImpl, NotificationServiceImp notificationServiceImp) {
        this.likeJpaRepository = likeJpaRepository;
        this.postServiceImpl = postServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.commentServicesImpl = commentServicesImpl;
        this.notificationServiceImp = notificationServiceImp;
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

//     to Delete a like 
    @Override
    public void deleteLike(Long idLike) {
        Optional<Likes> likeToEliminate = likeJpaRepository.findById(idLike);

        if (likeToEliminate.isEmpty()) {
            throw new InvalidInputException("Like not found with id: " + idLike);
        }

//    Comment comment = commentServicesImpl.findCommentByLike(idLike);
//    Post post = postServiceImpl.getPostByIdLike(idLike).getBody();
//    
//    if (comment == null && post == null) {
//        throw new NotFoundException("Comment and post not found for like with id: " + idLike);
//    }
//
//    if (comment != null) {
//        comment.removelike(likeToEliminate.get());
//        commentServicesImpl.savecomment(comment);
//    }
//
//    if (post != null) {
//        post.removeLike(likeToEliminate.get());
//        postServiceImpl.createPost(post);
//    }
        likeJpaRepository.deleteById(idLike);
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
    public ResponseEntity<LikePostDTO> createLikePost(Long idPost, String email) {

        Post postlike = postServiceImpl.getPostsByPostid(idPost);
        Users user = userServiceImpl.findUserbyEmail(email).getBody();

        if (hasUserLikedPost(postlike, user)) {
            return ResponseEntity.badRequest().build();
        }
        Likes like = new Likes(user, postlike);

        user.addLike(like);
        postlike.addLike(like);
        userServiceImpl.saveUserEntity(user);
        postServiceImpl.createPost(postlike);
        like = likeJpaRepository.save(like);
        notificationServiceImp.createNotificationLike(like);

        return ResponseEntity.ok(like.toLikePostDTO());

    }

    // To create  a like for a comment 
    @Override
    public ResponseEntity<LikeCommentDTO> createLikeComment(Long idComment, String email) {

        Comment commentlike = commentServicesImpl.findComentBYid(idComment).getBody();
        Users user = userServiceImpl.findUserbyEmail(email).getBody();
        if (hasUserLikedComment(commentlike, user)) {
            return ResponseEntity.badRequest().build();
        }
        Likes like = new Likes(user, commentlike);
        commentlike.addlike(like);
        user.addLike(like);
        commentServicesImpl.savecomment(commentlike);
        userServiceImpl.saveUserEntity(user);
        like = likeJpaRepository.save(like);
        notificationServiceImp.createNotificationLike(like);
        return ResponseEntity.ok(like.toLikeCommentDTO());

    }

}
