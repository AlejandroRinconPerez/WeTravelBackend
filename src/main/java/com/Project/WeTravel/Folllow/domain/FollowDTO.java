package com.Project.WeTravel.Folllow.domain;

import com.Project.WeTravel.Users.domain.Users;
import java.util.Date;

public class FollowDTO {

    private Date reactionDate = new Date();
    private String userNameFollowed;
    private String userNameFollower;


    public FollowDTO() {
    }

    public FollowDTO(String userNameFollowed, String userNameFollower) {
        this.userNameFollowed = userNameFollowed;
        this.userNameFollower = userNameFollower;
    }

    public Date getReactionDate() {
        return reactionDate;
    }

    public void setReactionDate(Date reactionDate) {
        this.reactionDate = reactionDate;
    }

    public String getUserNameFollowed() {
        return userNameFollowed;
    }

    public void setUserNameFollowed(String userNameFollowed) {
        this.userNameFollowed = userNameFollowed;
    }

    public String getUserNameFollower() {
        return userNameFollower;
    }

    public void setUserNameFollower(String userNameFollower) {
        this.userNameFollower = userNameFollower;
    }

    

}
