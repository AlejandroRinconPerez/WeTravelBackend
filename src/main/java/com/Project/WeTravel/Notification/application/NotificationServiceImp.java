/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Notification.application;

import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Notification.infrastructure.NotificationJpaRepository;
import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Users.domain.Users;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImp implements NotificationService {

    private final NotificationJpaRepository notificationJpaRepository;

    public NotificationServiceImp(NotificationJpaRepository notificationJpaRepository) {
        this.notificationJpaRepository = notificationJpaRepository;
    }

    @Override
    public void createFollowNotification(Users follower, Users followed) {
        Notification notification = new Notification();
        notification.setStatus(false);
        notification.setNotificationDate(new Date());

        Follow follow = new Follow(followed, follower);
        notification.setUser(follow);  
        notification.setTo_user(followed);  

        notificationJpaRepository.save(notification);
    }

    @Override
    public void markAsRead(Long notificationId) {
        Notification notification = notificationJpaRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setStatus(false);
        notificationJpaRepository.save(notification);
    }

    public List<Notification> getUserNotifications(Users user, Boolean status) {
        return notificationJpaRepository.findByToUserAndStatus(user, status);
    }

}
