package com.Project.WeTravel.Folllow.application;

import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Folllow.domain.FollowDTO;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import java.util.List;

public interface FollowService {

    Follow followUser(String emailfollower, String emailfollowed);

    void unfollowUser(String emailfollower, String emailfollowed);

    List<UsersDTO> getFollowers(String userName);

    List<UsersDTO> getFollowing(String userName);
}
