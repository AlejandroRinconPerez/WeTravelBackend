package com.Project.WeTravel.Notification.domain;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Folllow.domain.FollowDTO;
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

    public NotificationDTO toNotificationDTO() {
        NotificationDTO notificationDto = new NotificationDTO();

        notificationDto.setNotificationDate(this.getNotificationDate());
        notificationDto.setStatus(this.status);
        notificationDto.setIdNotification(this.idNotification);
        notificationDto.setToUser(this.toUser);

        // Inicializa relaciones perezosas si es necesario
        if (this.follow != null) {
            this.follow.getIdData(); // Inicializa la relación perezosa
        }
        if (this.comment != null) {
            this.comment.getIdComment(); // Inicializa la relación perezosa
        }
        if (this.like != null) {
            this.like.getIdLike(); // Inicializa la relación perezosa
        }

        return notificationDto;
    }

    public static Notification fromDTO(NotificationDTO notificationDTO) {
        Notification notification = new Notification();

        notification.setNotificationDate(notificationDTO.getNotificationDate());
        notification.setStatus(notificationDTO.getStatus());
        notification.setIdNotification(notificationDTO.getIdNotification());
        notification.setToUser(notificationDTO.getToUser());

        return notification;
    }

    @Override
    public String toString() {
        return "Notification{" + "idNotification=" + idNotification + ", status=" + status + ", notificationDate=" + notificationDate + ", follow=" + follow + ", comment=" + comment + ", like=" + like + ", toUser=" + toUser + '}';
    }

}
