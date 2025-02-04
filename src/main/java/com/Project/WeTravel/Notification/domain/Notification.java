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
    private Long idNotification;

    private Boolean status;
    private Date notificationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_follow", nullable = true)
    private Follow follow;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comment", nullable = true)
    private Comment comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_like", nullable = true)
    private Likes like;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reciber")
    private Users toUser;

    public Notification() {
    }

    public Notification(Boolean status, Date notificationDate, Follow follow, Users toUser) {
        this.status = status;
        this.notificationDate = notificationDate;
        this.follow = follow;
        this.toUser = toUser;
    }

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
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

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }

    public Users getToUser() {
        return toUser;
    }

    public void setToUser(Users toUser) {
        this.toUser = toUser;
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

    @Override
    public String toString() {
        return "Notification{" + "idNotification=" + idNotification + ", status=" + status + ", notificationDate=" + notificationDate + ", follow=" + follow + ", toUser=" + toUser + '}';
    }

}
