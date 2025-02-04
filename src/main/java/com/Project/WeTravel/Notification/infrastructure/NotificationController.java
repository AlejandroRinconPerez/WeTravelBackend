package com.Project.WeTravel.Notification.infrastructure;

import com.Project.WeTravel.Notification.application.NotificationServiceImp;
import java.util.List;
import com.Project.WeTravel.Notification.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationServiceImp notificationService;

    @GetMapping("/{username}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable String username) {
        List<Notification> notifications = notificationService.getUserNotifications(username);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/read/{notificationId}")
    public ResponseEntity<String> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok("Notification marked as read");
    }
//
//    @GetMapping("/unread/{username}")
//    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable String username) {
//        List<Notification> notifications = notificationService.getUserNotificationsStatus(username); 
//        return ResponseEntity.ok(notifications);
//    }
}
