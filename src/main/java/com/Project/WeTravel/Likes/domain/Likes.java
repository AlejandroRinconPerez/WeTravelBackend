package com.Project.WeTravel.Likes.domain;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.application.DTO.LikeCommentDTO;
import com.Project.WeTravel.Likes.application.DTO.LikePostDTO;
import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "Likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iduser", nullable = false)
    private Users user;      // id de quien lo hace 

    @Temporal(TemporalType.TIMESTAMP)
    private Date reactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPost")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcomment")
    private Comment comment;

    @OneToOne(
            mappedBy = "like", // Debe coincidir con el nombre del campo en Perfil
            cascade = CascadeType.ALL, // Operaciones en cascada
            fetch = FetchType.LAZY
    )
    private Notification notification;

    public Likes() {
    }
// like para Post
    public Likes(Users user, Post post) {
        this.user = user;
        this.reactionDate = new Date ();
        this.post = post;
    }
// Like para Comentario 
    public Likes(Users user, Comment comment) {
        this.user = user;
        this.reactionDate = new Date();
        this.comment = comment;
    }

   
    
    
    
    
    
    
    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getReactionDate() {
        return reactionDate;
    }

    public void setReactionDate(Date reactionDate) {
        this.reactionDate = reactionDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public LikeCommentDTO toLikeCommentDTO() {
        LikeCommentDTO likeCommentDTO = new LikeCommentDTO();
        likeCommentDTO.setIdLike(this.idLike);
        likeCommentDTO.setIdUser(this.user.getIdUser());
        likeCommentDTO.setUserName(this.user.getName()); 
        likeCommentDTO.setUserProfilePhoto(this.user.getPhoto()); 
        likeCommentDTO.setIdComment(this.comment.getIdComment());
        likeCommentDTO.setReactionDate(this.reactionDate);

        return likeCommentDTO;
    }

    public static Likes fromLikeCommentDTO(LikeCommentDTO likeCommentDTO) {
        Likes like = new Likes();
        like.setIdLike(likeCommentDTO.getIdLike());
        Users user = new Users();
        user.setIdUser(likeCommentDTO.getIdUser());
        user.setName(likeCommentDTO.getUserName()); 
        user.setPhoto(likeCommentDTO.getUserProfilePhoto()); 
        like.setUser(user);
        Comment comment = new Comment();
        comment.setIdComment(likeCommentDTO.getIdComment());
        like.setComment(comment);
        like.setReactionDate(likeCommentDTO.getReactionDate());

        return like;
    }

    public LikePostDTO toLikePostDTO() {
        LikePostDTO likePostDTO = new LikePostDTO();
        likePostDTO.setIdLike(this.idLike);
        likePostDTO.setIdUser(this.user.getIdUser());
        likePostDTO.setUserName(this.user.getName()); 
        likePostDTO.setUserProfilePhoto(this.user.getPhoto()); 
        likePostDTO.setIdPost(this.post.getIdPost());
        likePostDTO.setReactionDate(this.reactionDate);

        return likePostDTO;
    }

    public static Likes fromLikePostDTO(LikePostDTO likePostDTO) {
        Likes like = new Likes();
        like.setIdLike(likePostDTO.getIdLike());
        Users user = new Users();
        user.setIdUser(likePostDTO.getIdUser());
        user.setName(likePostDTO.getUserName()); 
        user.setPhoto(likePostDTO.getUserProfilePhoto()); 
        like.setUser(user);
        Post post = new Post();
        post.setIdPost(likePostDTO.getIdPost());
        like.setPost(post);
        like.setReactionDate(likePostDTO.getReactionDate());

        return like;
    }

    
    @Override
    public String toString() {
        return "Likes{" + "idLike=" + idLike
                + ", user=" + user
                + ", reactionDate=" + reactionDate
                + ", post=" + post
                + ", comment=" + comment
                + ", notification=" + notification + '}';
    }

}
