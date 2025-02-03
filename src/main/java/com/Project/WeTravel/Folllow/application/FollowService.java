package com.Project.WeTravel.Folllow.application;

import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Folllow.domain.FollowDTO;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import java.util.List;

public interface FollowService {

    Follow followUser(Long idfollower, Long idfollowed);

    void unfollowUser(Long idfollower, Long idfollowed);

    List<UsersDTO> getFollowers(String userName);

    List<UsersDTO> getFollowing(String userName);
}
