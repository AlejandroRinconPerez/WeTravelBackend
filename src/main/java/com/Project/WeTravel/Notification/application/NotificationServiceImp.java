/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Notification.application;

import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Notification.infrastructure.NotificationJpaRepository;
import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Folllow.infrastructure.FollowJpaRepository;
import com.Project.WeTravel.Notification.domain.NotificationDTO;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Users.infrastructure.UserJpaRepositorty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

        if (followed.isPresent()) {
            Notification notification = new Notification();
            notification.setStatus(false);
            notification.setNotificationDate(new Date());
            notification.setFollow(follow);
            notification.setToUser(followed.get());
            Users user = followed.get();

            user.addNotification(notification);
            userJpaRepositorty.save(user);
            notificationJpaRepository.save(notification);
        } else {
            // Log para indicar que el usuario no fue encontrado
            System.out.println("Usuario seguido no encontrado: " + follow.getFollowed().getUserName());
        }
    }

    @Override
    public void markAsRead(Long notificationId) {
        Notification notification = notificationJpaRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setStatus(true);
        notificationJpaRepository.save(notification);
    }

    public List<Notification> getNotifications() {

        return notificationJpaRepository.findAll();

    }

//    public List<Notification> getUserNotificationsStatus(String username) {
//        Optional<Users> user = userJpaRepositorty.findByuserName(username);
//        return notificationJpaRepository.findByToUserAndStatus(user.get().getIdUser(), true);
//    }
    @Override
    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationJpaRepository.findAll();
        if (notifications == null || notifications.isEmpty()) {
            return new ArrayList<>();
        }
        return notifications.stream()
                .map(notification -> notification.toNotificationDTO())
                .collect(Collectors.toList());
    }

    public List<Notification> getUserNotifications(String username) {
        Optional<Users> user = userJpaRepositorty.findByuserName(username);
        if (user.isPresent()) {
            Users foundUser = user.get();
            List<Notification> notifications = notificationJpaRepository.findBytoUser(foundUser);
            System.out.println("Notifications for user " + username + ": " + notifications);
            return notifications;
        } else {
            System.out.println("User not found: " + username);
        }
        return new ArrayList<>();
    }

}
