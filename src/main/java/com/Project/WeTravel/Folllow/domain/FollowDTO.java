package com.Project.WeTravel.Folllow.domain;

import com.Project.WeTravel.Users.domain.Users;
import java.util.Date;

public class FollowDTO {

    private Users follower;
    private Users followed;
    private Date dateFollow = new Date();

    public FollowDTO() {
    }

    public FollowDTO(Users follower, Users followed) {
        this.follower = follower;
        this.followed = followed;
        this.dateFollow = new Date();
    }

    public Users getFollower() {
        return follower;
    }

    public void setFollower(Users follower) {
        this.follower = follower;
    }

    public Users getFollowed() {
        return followed;
    }

    public void setFollowed(Users followed) {
        this.followed = followed;
    }

    public Date getDateFollow() {
        return dateFollow;
    }

    public void setDateFollow(Date dateFollow) {
        this.dateFollow = dateFollow;
    }

    @Override
    public String toString() {
        return "FollowDTO{" + "follower=" + follower + ", followed=" + followed + ", dateFollow=" + dateFollow + '}';
    }

}
