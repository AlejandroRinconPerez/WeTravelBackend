package com.Project.WeTravel.Notification.domain;

import com.Project.WeTravel.Users.domain.Users;
import java.util.Date;


public class NotificationDTO {

    private Long idNotification;

    private Boolean status;
    private Date notificationDate;

    private Users toUser;

    public NotificationDTO() {
    }

    public NotificationDTO(Long idNotification, Boolean status, Date notificationDate, Users toUser) {
        this.idNotification = idNotification;
        this.status = status;
        this.notificationDate = notificationDate;
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

    public Users getToUser() {
        return toUser;
    }

    public void setToUser(Users toUser) {
        this.toUser = toUser;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" + "idNotification=" + idNotification + ", status=" + status + ", notificationDate=" + notificationDate + ", toUser=" + toUser + '}';
    }

    
}
