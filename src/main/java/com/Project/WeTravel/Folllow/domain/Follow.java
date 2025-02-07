package com.Project.WeTravel.Folllow.domain;

import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Users.domain.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followedId")
    private Users followed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followerId")
    private Users follower;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfFollowing;
    
    @OneToOne(mappedBy = "follow", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Notification notification;

    public Follow() {
    }

    public Follow(Users followed, Users follower) {
        this.followed = followed;
        this.follower = follower;
        this.dateOfFollowing = new Date();
    }

    public Long getIdData() {
        return idData;
    }

    public void setIdData(Long idData) {
        this.idData = idData;
    }

    public Users getFollowed() {
        return followed;
    }

    public void setFollowed(Users followed) {
        this.followed = followed;
    }

    public Users getFollower() {
        return follower;
    }

    public void setFollower(Users follower) {
        this.follower = follower;
    }

    public Date getDateOfFollowing() {
        return dateOfFollowing;
    }

    public void setDateOfFollowing(Date dateOfFollowing) {
        this.dateOfFollowing = dateOfFollowing;
    }

  

}
