/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Comments.domain;

import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Likes.application.DTO.LikeCommentDTO;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    private String content;

    private Date createDate;

    private Date upDatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iduser", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPost", nullable = false)
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Likes> likeList = new ArrayList<>();

    @OneToOne(
            mappedBy = "comment", // Debe coincidir con el nombre del campo en Perfil
            cascade = CascadeType.ALL, // Operaciones en cascada
            fetch = FetchType.LAZY
    )
    private Notification notification;

    public Comment() {
    }

    
    
    
   public Comment(String content) {
        this.content = content;
        this.createDate = new Date(); 
    }

    
    
    
    
    
    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpDatedAt() {
        return upDatedAt;
    }

    public void setUpDatedAt(Date upDatedAt) {
        this.upDatedAt = upDatedAt;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public List<Likes> getLikeList() {
        return likeList;
    }

    public void addlike(Likes like) {
        this.likeList.add(like);
        like.setComment(this);
    }

    public void removelike(Likes like) {
        this.likeList.remove(like);
        like.setComment(null);
    }

    public void setLikeList(List<Likes> likeList) {
        this.likeList = likeList;
    }

    
    
    
    public CommentDTO toDTO() {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setIdComment(this.idComment);
    commentDTO.setContent(this.content);
    commentDTO.setCreateDate(this.createDate);
    commentDTO.setUserName(this.user.getName());
    commentDTO.setUserProfilePhoto(this.user.getPhoto());
  
    return commentDTO;
}

public static Comment fromDTO(CommentDTO commentDTO) {
    Comment comment = new Comment();
    comment.setIdComment(commentDTO.getIdComment());
    comment.setContent(commentDTO.getContent());
    comment.setCreateDate(commentDTO.getCreateDate());

    Users user = new Users();
    user.setName(commentDTO.getUserName());
    user.setPhoto(commentDTO.getUserProfilePhoto());
    comment.setUser(user);

   
    return comment;
}

   
    
    @Override
    public String toString() {
        return "Comment{" + "idComment=" + idComment
                + ", content=" + content
                + ", createDate=" + createDate
                + ", upDatedAt=" + upDatedAt
                + ", user=" + user
                + ", post=" + post
                + ", likeList=" + likeList
                + ", notification=" + notification + '}';
    }

}
