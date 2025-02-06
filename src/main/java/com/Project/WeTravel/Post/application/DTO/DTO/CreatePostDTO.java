package com.Project.WeTravel.Post.application.DTO.DTO;

import com.Project.WeTravel.Likes.application.DTO.LikePostDTO;
import com.Project.WeTravel.Photo.domain.Photo;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Tags.domain.Tag;
import com.Project.WeTravel.Users.domain.Users;
import java.util.Date;
import java.util.List;

public class CreatePostDTO {

    private String description;
    private Date creationDate;
    private Date updatedDate;
    private List<String> listTag;
    private List<String> listPhoto;

    public CreatePostDTO() {
        this.creationDate = new Date();
        this.updatedDate = new Date();
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

    public List<String> getListTag() {
        return listTag;
    }

    public void setListTag(List<String> listTag) {
        this.listTag = listTag;
    }

    public List<String> getListPhoto() {
        return listPhoto;
    }

    public void setListPhoto(List<String> listPhoto) {
        this.listPhoto = listPhoto;
    }

    public void addTagtoPost(String tag) {
        this.listTag.add(tag);

    }

}
