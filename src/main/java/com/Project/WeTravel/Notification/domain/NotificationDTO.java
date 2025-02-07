package com.Project.WeTravel.Notification.domain;

import com.Project.WeTravel.Users.domain.Users;
import java.sql.Time;
import java.util.Date;


public class NotificationDTO {

    private Long idNotification;
    private String email; // quien realizó la acción (like, comment, follow)
    private String userPhoto;
    private Date date; // fecha de la notificación
    private Time hora; // hora de la notificación
    private String tipo; // tipo de notificación (Follow, Like, Comment)
    private Long idFollow; // solo para tipo Follow
    private Long idLike; // solo para tipo Like
    private Long idComment; // solo para tipo Comment
    private String emailRecipient; // usuario que recibe la notificación


        public NotificationDTO() {
    }
    
    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdFollow() {
        return idFollow;
    }

    public void setIdFollow(Long idFollow) {
        this.idFollow = idFollow;
    }

    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public String getEmailRecipient() {
        return emailRecipient;
    }

    public void setEmailRecipient(String emailRecipient) {
        this.emailRecipient = emailRecipient;
    }
}