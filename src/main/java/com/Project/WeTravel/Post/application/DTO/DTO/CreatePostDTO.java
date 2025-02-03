package com.Project.WeTravel.Post.application.DTO.DTO;

import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.domain.Users;
import java.util.Date;

public class CreatePostDTO {

    private Users user;
    private String description;
    private Date creationDate;
    private Date updatedDate;

   public CreatePostDTO() {
    this.creationDate = new Date();
    this.updatedDate = new Date();
}

    public CreatePostDTO(Users user, String description, Date creationDate, Date updatedDate) {
        this.user = user;
        this.description = description;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

 

}
