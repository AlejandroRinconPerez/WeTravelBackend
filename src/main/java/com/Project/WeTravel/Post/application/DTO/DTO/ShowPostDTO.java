
package com.Project.WeTravel.Post.application.DTO.DTO;


import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Photo.domain.Photo;
import com.Project.WeTravel.Tags.domain.Tag;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import java.util.Date;
import java.util.List;

import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import java.util.Date;

public class ShowPostDTO {

    
    private Long postid; 
    private Date creationDate;
    private UsersDTO user;
    private Date updatedDate;
    private String description;
   
   

    public ShowPostDTO() {
    }

    public ShowPostDTO(Long postid, Date creationDate, UsersDTO user, Date updatedDate, String description) {
        this.postid = postid;
        this.creationDate = creationDate;
        this.user = user;
        this.updatedDate = updatedDate;
        this.description = description;
    }

    public Long getPostid() {
        return postid;
    }

    public void setPostid(Long postid) {
        this.postid = postid;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
}