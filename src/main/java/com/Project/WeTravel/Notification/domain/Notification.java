package com.Project.WeTravel.Notification.domain;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Users.domain.Users;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotofication;

    private Boolean status;
    private Date notificationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comment", nullable = true)
    private Comment comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_like", nullable = true)
    private Likes like;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_follower", nullable = true)
    private Follow user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reciber")
    private Users to_user;

    public Notification() {
    }

    public Notification(Long idNotofication, Boolean status, Date notificationDate, Comment comment, Users to_user) {
        this.idNotofication = idNotofication;
        this.status = status;
        this.notificationDate = notificationDate;
        this.comment = comment;
        this.to_user = to_user;
    }

    public Notification(Long idNotofication, Boolean status, Date notificationDate, Likes like, Users to_user) {
        this.idNotofication = idNotofication;
        this.status = status;
        this.notificationDate = notificationDate;
        this.like = like;
        this.to_user = to_user;
    }

    public Notification(Long idNotofication, Boolean status, Date notificationDate, Follow user, Users to_user) {
        this.idNotofication = idNotofication;
        this.status = status;
        this.notificationDate = notificationDate;
        this.user = user;
        this.to_user = to_user;
    }

    public Long getIdNotofication() {
        return idNotofication;
    }

    public void setIdNotofication(Long idNotofication) {
        this.idNotofication = idNotofication;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Likes getLike() {
        return like;
    }

    public void setLike(Likes like) {
        this.like = like;
    }

    public Follow getUser() {
        return user;
    }

    public void setUser(Follow user) {
        this.user = user;
    }

    public Users getTo_user() {
        return to_user;
    }

    public void setTo_user(Users to_user) {
        this.to_user = to_user;
    }

    @Override
    public String toString() {
        return "Notification{" + "idNotofication=" + idNotofication
                + ", status=" + status
                + ", notificationDate=" + notificationDate
                + ", comment=" + comment
                + ", like=" + like
                + ", user=" + user
                + ", to_user=" + to_user + '}';
    }

}
