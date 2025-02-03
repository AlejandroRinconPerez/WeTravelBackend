package com.Project.WeTravel.Folllow.infrastructure;

import com.Project.WeTravel.Folllow.application.FollowServiceImpl;
import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Folllow.domain.FollowDTO;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Autowired
    private FollowServiceImpl followService;

    @PostMapping("/add/{idfollowed}/{idfollower}")
    public ResponseEntity<?> followUser(@PathVariable Long idfollowed, @PathVariable Long idfollower ) {
        try {
            Follow createdFollow = followService.followUser(idfollowed,idfollower );
            return ResponseEntity.ok(createdFollow.toFollowDTO());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/unfollow/{idfollowed}/{idfollower}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long idfollowed, @PathVariable Long idfollower ) {
        followService.unfollowUser(idfollowed,idfollower);
        return ResponseEntity.ok("Unfollowed successfully");
    }

    @GetMapping("/followers/{userName}")
    public ResponseEntity<List<UsersDTO>> getFollowers(@PathVariable String userName) {
        List<UsersDTO> followers = followService.getFollowers(userName);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/following/{userName}")
    public ResponseEntity<List<UsersDTO>> getFollowing(@PathVariable String userName) {
        List<UsersDTO> following = followService.getFollowing(userName);
        return ResponseEntity.ok(following);
    }
}
