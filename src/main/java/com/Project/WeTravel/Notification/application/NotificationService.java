package com.Project.WeTravel.Notification.application;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Notification.application.DTO.NotificationCommentDTO;
import com.Project.WeTravel.Notification.application.DTO.NotificationFolowerDTO;
import com.Project.WeTravel.Notification.application.DTO.NotificationLikeDTO;
import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Notification.domain.NotificationDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface NotificationService {

    // Crear una notificación de seguimiento
    public void createFollowNotification(Follow follow);

    // Marcar una notificación como leída
    Notification markAsRead(Long notificationId);

    // Obtener todas las notificaciones
    List<NotificationDTO> getAllNotifications();

    // Métodos adicionales
    ResponseEntity<NotificationFolowerDTO> createNotificationFollow(Follow follow);
    ResponseEntity<NotificationLikeDTO> createNotificationLike(Likes like);
    ResponseEntity<NotificationCommentDTO> createNotificationFollow(Comment comment);
    List<Notification> getNotifications();
    List<NotificationDTO> getUserNotifications(String username);
    void changeNotificationStaatus(Long idNotification);
}
