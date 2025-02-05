/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Likes.application.DTO;




import java.util.Date;

public class LikeCommentDTO {

    private Long idLike;
    private Long idUser;
    private Long idComment;
    private Date reactionDate;
    private String userName; 
    private String userProfilePhoto; 

    public LikeCommentDTO() {
    }



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

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
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
        return "LikeCommentDTO{" +
                "idLike=" + idLike +
                ", idUser=" + idUser +
                ", idComment=" + idComment +
                ", reactionDate=" + reactionDate +
                ", userName='" + userName + '\'' +
                ", userProfilePhoto='" + userProfilePhoto + '\'' +
                '}';
    }
}
