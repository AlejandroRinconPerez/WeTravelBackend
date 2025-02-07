package com.Project.WeTravel.Folllow.application;

import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Folllow.domain.FollowDTO;
import com.Project.WeTravel.Folllow.infrastructure.FollowJpaRepository;
import com.Project.WeTravel.Notification.application.NotificationServiceImp;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import com.Project.WeTravel.Users.application.UserServiceImpl;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Users.infrastructure.UserJpaRepositorty;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowJpaRepository followJpaRepository;
    private final UserServiceImpl userService;
    private final NotificationServiceImp notificationService;

    @Autowired
    public FollowServiceImpl(FollowJpaRepository followJpaRepository, UserServiceImpl userService, NotificationServiceImp notificationService) {
        this.followJpaRepository = followJpaRepository;
        this.userService = userService;
        this.notificationService = notificationService;

    }

    @Transactional
    @Override
    public Follow followUser(String emailfollower, String emailfollowed) {
        ResponseEntity<Users> optionalFollower = userService.findUserbyEmail(emailfollower);
        ResponseEntity<Users> optionalFollowed = userService.findUserbyEmail(emailfollowed);

        if (optionalFollower.getBody() != null && optionalFollowed.getBody() != null) {
            Users follower = optionalFollower.getBody();
            Users followed = optionalFollowed.getBody();

            if (follower.getUserName().equals(followed.getUserName())) {
                throw new IllegalArgumentException("You cannot follow yourself.");
            }

            Follow follow = new Follow(followed, follower);

            follow = followJpaRepository.save(follow);
            notificationService.createNotificationFollow(follow);

            return follow;
        } else {
            throw new EntityNotFoundException("Follower or followed user not found.");
        }
    }

    @Transactional
    @Override
    public void unfollowUser(String emailfollower, String emailfollowed) {

        ResponseEntity<Users> optionalFollower = userService.findUserbyEmail(emailfollower);
        ResponseEntity<Users> optionalFollowed = userService.findUserbyEmail(emailfollowed);

         if (optionalFollower.getBody() != null && optionalFollowed.getBody() != null) {
            Users follower = optionalFollower.getBody();
            Users followed = optionalFollowed.getBody();
                    
            Optional<Follow> followToRemove = followJpaRepository.findByFollowerUserNameAndFollowedUserName(
                    follower.getUserName(), followed.getUserName());

            if (followToRemove.isPresent()) {
                followJpaRepository.delete(followToRemove.get());
                System.out.println("Follow removed successfully");
            }
        } else {
            throw new EntityNotFoundException("Follower or followed user not found.");
        }
    }

    @Override
    public List<UsersDTO> getFollowers(String userName
    ) {
        List<Follow> followers = followJpaRepository.findByFollowed_UserName(userName);
        return followers.stream()
                .map(follow -> toUsersDTO(follow.getFollower()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsersDTO> getFollowing(String userName
    ) {
        List<Follow> following = followJpaRepository.findByFollower_UserName(userName);
        return following.stream()
                .map(follow -> toUsersDTO(follow.getFollowed()))
                .collect(Collectors.toList());
    }
/// Nota Para jaime Ojo con ese DTO  que no es oficial si se quita marca errores 

    private UsersDTO toUsersDTO(Users user) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUserName(user.getUserName());
        usersDTO.setEmail(user.getEmail());
        usersDTO.setName(user.getName());
        usersDTO.setBiography(user.getBiography());
        usersDTO.setPhoto(user.getPhoto());
        usersDTO.setCreationDate(user.getCreationDate());
        usersDTO.setLastLogin(user.getLastLogin());
        usersDTO.setEditionDate(user.getEditionDate());
        return usersDTO;
    }
}
