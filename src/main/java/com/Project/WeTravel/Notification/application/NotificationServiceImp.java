/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Notification.application;

import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Notification.infrastructure.NotificationJpaRepository;
import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Folllow.infrastructure.FollowJpaRepository;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Users.infrastructure.UserJpaRepositorty;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImp implements NotificationService {

    private final NotificationJpaRepository notificationJpaRepository;
    private final FollowJpaRepository followJpaRepository;
    private final UserJpaRepositorty userJpaRepositorty;

    @Autowired
    public NotificationServiceImp(NotificationJpaRepository notificationJpaRepository, FollowJpaRepository followJpaRepository, UserJpaRepositorty userJpaRepositorty) {
        this.notificationJpaRepository = notificationJpaRepository;
        this.followJpaRepository = followJpaRepository;
        this.userJpaRepositorty = userJpaRepositorty;
    }

    @Override
    public void createFollowNotification(Follow follow) {
        Optional<Users> followed = userJpaRepositorty.findByuserName(follow.getFollowed().getUserName());

        if (follow != null) {
            Notification notification = new Notification();
            notification.setStatus(false);
            notification.setNotificationDate(new Date());
            notification.setFollow(follow);
            notification.setToUser(followed.get());

            notificationJpaRepository.save(notification);
        }
    }

    @Override
    public void markAsRead(Long notificationId) {
        Notification notification = notificationJpaRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setStatus(true);
        notificationJpaRepository.save(notification);
    }

    public List<Notification> getUserNotifications(String username) {

        Optional<Users> user = userJpaRepositorty.findByuserName(username);
        if (user.isPresent()) {
            return notificationJpaRepository.findBytoUser(user.get());

        }
        return null;
    }

//    public List<Notification> getUserNotificationsStatus(String username) {
//        Optional<Users> user = userJpaRepositorty.findByuserName(username);
//        return notificationJpaRepository.findByToUserAndStatus(user.get().getIdUser(), true);
//    }

}
