package com.Project.WeTravel.Folllow.infrastructure;

import com.Project.WeTravel.Folllow.domain.Follow;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByFollower_UserName(String followerUserName);

    List<Follow> findByFollowed_UserName(String followedUserName);

    Optional<Follow> findByFollowerUserNameAndFollowedUserName(String followerUserName, String followedUserName);
}
