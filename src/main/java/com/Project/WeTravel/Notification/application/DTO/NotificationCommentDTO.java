package com.Project.WeTravel.Notification.application.DTO;

import java.sql.Time;
import java.util.Date;

public class NotificationCommentDTO {

    private Long idNotification;
    private String email; // quien dio like 
    private String userPhoto;
    private Date date; //  automatico al rearla 
    private Time hora;
    private String tipo;
    private Long idComment;
    private String emailreciber; //user quien recibe la notificacion

    public NotificationCommentDTO() {

        this.setDate(new Date());
        this.setHora(new Time(this.date.getTime()));
        this.setTipo("Comment");

    }

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public String getEmial() {
        return email;
    }

    public void setEmial(String emial) {
        this.email = emial;
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

    public Long getidComment() {
        return idComment;
    }

    public void setidComment(Long idComment) {
        this.idComment = idComment;
    }

    public String getEmailreciber() {
        return emailreciber;
    }

    public void setEmailreciber(String emailreciber) {
        this.emailreciber = emailreciber;
    }

}
