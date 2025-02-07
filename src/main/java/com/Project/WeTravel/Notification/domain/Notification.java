package com.Project.WeTravel.Notification.domain;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Notification.application.DTO.NotificationCommentDTO;
import com.Project.WeTravel.Notification.application.DTO.NotificationFolowerDTO;
import com.Project.WeTravel.Notification.application.DTO.NotificationLikeDTO;
import com.Project.WeTravel.Users.domain.Users;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;

    private Boolean status;
    @Temporal(TemporalType.TIMESTAMP) // Almacena fecha y hora
    @Column(updatable = false) // No se actualiza después de la creación
    private Date notificationDate;

    private Time hora; // Solo la hora (se calcula desde notificationDate)

    @OneToOne(fetch = FetchType.LAZY)
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

    public Notification(Follow follow) {
        this.status = false;
        this.notificationDate = new Date();
        this.setHora(new Time(notificationDate.getTime()));
        this.follow = follow;
        this.setToUser(this.follow.getFollowed()); 
    }

    public Notification(Comment comment) {
        this.status = false;
        this.notificationDate = new Date();
        this.setHora(new Time(notificationDate.getTime()));
        this.comment = comment;
       this.setToUser(this.comment.getUser());
    }

    public Notification( Likes like) {
        this.status = false;
        this.notificationDate = new Date();
        this.setHora(new Time(notificationDate.getTime()));
        this.like = like;
              this.setToUser(this.like.getUser()); 
    }
 
    
    
    
    
    
    
    
    public Notification(Date notificationDate, Follow follow, Users toUser) {
        this.status = false;
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

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
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

public NotificationDTO toDTO() {
    NotificationDTO dto = new NotificationDTO();
   
    dto.setIdNotification(this.idNotification);
    dto.setDate(this.notificationDate);
    dto.setHora(new Time( this.notificationDate.getTime()));
    dto.setEmailRecipient(this.toUser.getEmail()); 

    if (this.follow != null) {
        dto.setTipo("Follow");
        dto.setIdFollow(this.follow.getIdData());
        dto.setEmail(this.follow.getFollower().getEmail());
        dto.setUserPhoto(this.follow.getFollower().getPhoto());
    } else if (this.like != null) {
        dto.setTipo("Like");
        dto.setIdLike(this.like.getIdLike());
        dto.setEmail(this.like.getUser().getEmail());
         dto.setUserPhoto(this.like.getUser().getPhoto());
    } else if (this.comment != null) {
        dto.setTipo("Comment");
         dto.setEmail(this.comment.getUser().getEmail());
        dto.setIdComment(this.comment.getIdComment());
         dto.setUserPhoto(this.comment.getUser().getPhoto());
    }

    return dto;
}

//    public static Notification fromDTO(NotificationDTO notificationDTO) {
//        Notification notification = new Notification();
//
//        notification.setNotificationDate(notificationDTO.getNotificationDate());
//        notification.setStatus(notificationDTO.getStatus());
//        notification.setIdNotification(notificationDTO.getIdNotification());
//        notification.setToUser(notificationDTO.getToUser());
//
//        return notification;
//    }

    public NotificationLikeDTO toDTOLike() {

        NotificationLikeDTO notificationLikeDTO = new NotificationLikeDTO();
        notificationLikeDTO.setIdLike(this.idNotification);
        notificationLikeDTO.setEmailreciber(this.like.getUser().getEmail());
        notificationLikeDTO.setUserPhoto(this.like.getUser().getPhoto());
        notificationLikeDTO.setIdLike(this.getLike().getIdLike());
        notificationLikeDTO.setEmailreciber(this.getToUser().getEmail());
        return notificationLikeDTO;

    }

    public NotificationCommentDTO toDTOComment() {
        NotificationCommentDTO notificationCommentDTO = new NotificationCommentDTO();
        notificationCommentDTO.setIdNotification(this.idNotification);
        notificationCommentDTO.setUserPhoto(this.comment.getUser().getPhoto());
        notificationCommentDTO.setEmial(this.comment.getUser().getEmail());
        notificationCommentDTO.setidComment(this.comment.getIdComment());
        notificationCommentDTO.setEmailreciber(this.toUser.getEmail());
        return notificationCommentDTO;

    }

    public NotificationFolowerDTO toDTOFollow() {
        NotificationFolowerDTO notificationFolowerDTO = new NotificationFolowerDTO();

        notificationFolowerDTO.setIdNotification(this.idNotification);
        notificationFolowerDTO.setEmial(this.follow.getFollower().getEmail());
        notificationFolowerDTO.setUserPhoto(this.follow.getFollower().getPhoto());
        notificationFolowerDTO.setIdFollow(this.follow.getIdData());
        notificationFolowerDTO.setEmailreciber(this.toUser.getEmail());
        return notificationFolowerDTO;

    }

    @Override
    public String toString() {
        return "Notification{" + "idNotification=" + idNotification + ", status=" + status + ", notificationDate=" + notificationDate + ", follow=" + follow + ", comment=" + comment + ", like=" + like + ", toUser=" + toUser + '}';
    }

}
