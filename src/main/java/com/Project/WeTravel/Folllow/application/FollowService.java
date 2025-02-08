package com.Project.WeTravel.Folllow.application;

import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import java.util.List;

public interface FollowService {

    // Seguir a un usuario
    Follow followUser(String emailfollower, String emailfollowed);

    // Dejar de seguir a un usuario
    void unfollowUser(String emailfollower, String emailfollowed);

    // Obtener la lista de seguidores de un usuario específico
    List<UsersDTO> getFollowers(String userName);

    // Obtener la lista de usuarios seguidos por un usuario específico
    List<UsersDTO> getFollowing(String userName);
}
