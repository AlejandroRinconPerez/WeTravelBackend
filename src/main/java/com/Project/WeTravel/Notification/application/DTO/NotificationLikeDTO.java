
package com.Project.WeTravel.Notification.application.DTO;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;


public class NotificationLikeDTO {
    
    
    private Long idNotification;
    private String emial; // quien dio like 
    private String userPhoto;
    private Date date; //  automatico al rearla 
    private Time  hora;
    private String tipo;
    private Long idLike;
    private String emailreciber; //user quien recibe la notificacion

    public NotificationLikeDTO() {
       this.setDate(new Date());
       this.setHora(new Time(this.date.getTime()));
       this.setTipo("Like");
        
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public String getEmailreciber() {
        return emailreciber;
    }

    public void setEmailreciber(String emailreciber) {
        this.emailreciber = emailreciber;
    }

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public String getUsername() {
        return emial;
    }

    public void setUsername(String username) {
        this.emial = username;
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

    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public String getUsernamereviber() {
        return emailreciber;
    }

    public void setUsernamereviber(String usernamereviber) {
        this.emailreciber = usernamereviber;
    }
    
    
    
   
}
