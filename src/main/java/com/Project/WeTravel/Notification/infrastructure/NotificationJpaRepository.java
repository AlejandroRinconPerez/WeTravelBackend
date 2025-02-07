package com.Project.WeTravel.Notification.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Project.WeTravel.Notification.domain.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationJpaRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n JOIN n.toUser u WHERE u.userName = :userName")
    List<Notification> findByToUserName(@Param("userName") String userName);

//    List<Notification> findByToUserAndStatus(Long id, Boolean status);
}
