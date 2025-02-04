package com.Project.WeTravel.Notification.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;

public interface NotificationJpaRepository extends JpaRepository<Notification, Long> {

    List<Notification> findBytoUser(Users user);

//    List<Notification> findByToUserAndStatus(Long id, Boolean status);
}
