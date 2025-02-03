package com.Project.WeTravel.Notification.infrastructure;

import com.Project.WeTravel.Notification.application.NotificationServiceImp;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Users.infrastructure.UserJpaRepositorty;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationServiceImp notificationService;

    @Autowired
    private UserJpaRepositorty userService;

    @GetMapping("/{username}")
    public List<Notification> getUserNotifications(@PathVariable String username,
            @RequestParam(defaultValue = "true") Boolean status) {
        Optional<Users> user = userService.findByuserName(username);
        return notificationService.getUserNotifications(user.get(), status);
    }

    @PostMapping("/read/{notificationId}")
    public String markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return "Notification marked as read";
    }
    
    @GetMapping("/unread/{username}")
    public List<Notification> getUnreadNotifications(@PathVariable String username) {
        Optional<Users> user = userService.findByuserName(username);
        return notificationService.getUserNotifications(user.get(), true); 
    }
}
