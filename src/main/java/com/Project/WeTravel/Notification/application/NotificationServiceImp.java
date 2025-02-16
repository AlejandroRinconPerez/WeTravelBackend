package com.Project.WeTravel.Notification.application;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Notification.infrastructure.NotificationJpaRepository;
import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Folllow.infrastructure.FollowJpaRepository;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Notification.application.DTO.NotificationCommentDTO;
import com.Project.WeTravel.Notification.application.DTO.NotificationFolowerDTO;
import com.Project.WeTravel.Notification.application.DTO.NotificationLikeDTO;
import com.Project.WeTravel.Notification.domain.NotificationDTO;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Users.infrastructure.UserJpaRepositorty;
import com.Project.WeTravel.Utilities.exceptions.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

//    @Override
//    public void createFollowNotification(Follow follow) {
//        Optional<Users> followed = userJpaRepositorty.findByuserName(follow.getFollowed().getUserName());
//
//        if (followed.isPresent()) {
//            Notification notification = new Notification();
//            notification.setStatus(false);
//            notification.setNotificationDate(new Date());
//            notification.setFollow(follow);
//            notification.setToUser(followed.get());
//            Users user = followed.get();
//
//            user.addNotification(notification);
//            userJpaRepositorty.save(user);
//            notificationJpaRepository.save(notification);
//        } else {
//            // Log para indicar que el usuario no fue encontrado
//            System.out.println("Usuario seguido no encontrado: " + follow.getFollowed().getUserName());
//        }
//    }
    @Override
    public ResponseEntity<NotificationFolowerDTO> createNotificationFollow(Follow follow) {
        Notification notificationfollow = new Notification(follow);
        notificationJpaRepository.save(notificationfollow);
        NotificationFolowerDTO notificationFolowerDTO = notificationfollow.toDTOFollow();
        return ResponseEntity.ok(notificationFolowerDTO);
    }
    
    @Override
    public ResponseEntity<NotificationLikeDTO> createNotificationLike(Likes like) {
        Notification notificationLike = new Notification(like);
        notificationLike = notificationJpaRepository.save(notificationLike);
        NotificationLikeDTO notificationLikeDTO = notificationLike.toDTOLike();
        return ResponseEntity.ok(notificationLikeDTO);
    }
    
    @Override
    public ResponseEntity<NotificationCommentDTO> createNotificationFollow(Comment comment) {
        Notification notificationComment = new Notification(comment);
        notificationComment = notificationJpaRepository.save(notificationComment);
        NotificationCommentDTO notificationCommentDTO = notificationComment.toDTOComment();
        return ResponseEntity.ok(notificationCommentDTO);
        
    }
    
    @Override
    public Notification markAsRead(Long notificationId) {
        Notification notification = notificationJpaRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setStatus(true);
       Notification noti = notificationJpaRepository.save(notification);
        return noti;
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
                .map(Notification::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<NotificationDTO> getUserNotifications(String username) {
        Optional<Users> user = userJpaRepositorty.findByuserName(username);
        if (user.isPresent()) {
            Users foundUser = user.get();
            List<Notification> notifications = notificationJpaRepository.findByToUserName(username);
            System.out.println("Notifications for user " + username + ": " + notifications);
            return notifications.stream()
                    .map(Notification::toDTO)
                    .collect(Collectors.toList());
        } else {
            System.out.println("User not found: " + username);
        }
        return new ArrayList<>();
    }
    
    @Override
    public void createFollowNotification(Follow follow) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void changeNotificationStaatus(Long idNotification) {
        
        Optional< Notification> notification = notificationJpaRepository.findById(idNotification);
        if (notification.isEmpty()) {
            throw new NotFoundException("Notification not found ");
            
        }
        notification.get().setStatus(true);
        notificationJpaRepository.save(notification.get());
        
    }
    
}
