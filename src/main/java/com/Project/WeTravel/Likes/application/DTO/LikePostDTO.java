
package com.Project.WeTravel.Likes.application.DTO;



import java.util.Date;

import java.util.Date;

public class LikePostDTO {

    private Long idLike;
    private Long idUser;
    private Long idPost;
    private Date reactionDate;
    private String userName; 
    private String userProfilePhoto; 

    // Getters y Setters

    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public Date getReactionDate() {
        return reactionDate;
    }

    public void setReactionDate(Date reactionDate) {
        this.reactionDate = reactionDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfilePhoto() {
        return userProfilePhoto;
    }

    public void setUserProfilePhoto(String userProfilePhoto) {
        this.userProfilePhoto = userProfilePhoto;
    }

    @Override
    public String toString() {
        return "LikePostDTO{" +
                "idLike=" + idLike +
                ", idUser=" + idUser +
                ", idPost=" + idPost +
                ", reactionDate=" + reactionDate +
                ", userName='" + userName + '\'' +
                ", userProfilePhoto='" + userProfilePhoto + '\'' +
                '}';
    }
}
