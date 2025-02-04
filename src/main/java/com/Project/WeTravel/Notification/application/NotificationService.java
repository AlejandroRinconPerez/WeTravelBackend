package com.Project.WeTravel.Notification.application;

import com.Project.WeTravel.Folllow.domain.Follow;

public interface NotificationService {

    public void createFollowNotification(Follow follow);

    void markAsRead(Long notificationId);

}
