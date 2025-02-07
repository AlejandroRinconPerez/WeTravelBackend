package com.Project.WeTravel.Notification.infrastructure;

import com.Project.WeTravel.Notification.application.NotificationServiceImp;
import java.util.List;
import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Notification.domain.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<NotificationDTO>> getUserNotifications(@PathVariable String username) {
        try {
            List<NotificationDTO> notifications = notificationService.getUserNotifications(username);
            System.out.println("JSON Response: " + notifications);
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            System.err.println("Error fetching notifications for user " + username + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/read/{notificationId}")
    public ResponseEntity<String> markAsRead(@PathVariable Long notificationId) {
        System.out.println("Marking notification as read: " + notificationId);
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok("Notification marked as read");
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getNotifications() {
        System.out.println("Fetching all notifications");
        List<NotificationDTO> notifications = notificationService.getAllNotifications();
        System.out.println("Found notifications: " + notifications);
        return ResponseEntity.ok(notifications);
    }
}
