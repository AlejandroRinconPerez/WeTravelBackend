package com.Project.WeTravel.Notification.application;


import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Notification.domain.NotificationDTO;
import java.util.List;

public interface NotificationService {

    public void createFollowNotification(Follow follow);

    Notification markAsRead(Long notificationId);

    List<NotificationDTO> getAllNotifications();

}
