package com.Project.WeTravel.Notification.application;

import com.Project.WeTravel.Users.domain.Users;

public interface NotificationService {

    public void createFollowNotification(Users follower, Users followed);

    void markAsRead(Long notificationId);

}
