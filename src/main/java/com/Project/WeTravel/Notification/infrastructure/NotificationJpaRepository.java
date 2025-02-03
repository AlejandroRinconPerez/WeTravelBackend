package com.Project.WeTravel.Notification.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationJpaRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.to_user = :user AND n.status = :status")
    List<Notification> findByToUserAndStatus(@Param("user") Users user, @Param("status") Boolean status);

    @Query("SELECT n FROM Notification n WHERE n.to_user = :user AND n.status = :status ORDER BY n.notificationDate DESC")
    List<Notification> findByToUserAndStatusOrderByNotificationDateDesc(@Param("user") Users user, @Param("status") Boolean status);

}
