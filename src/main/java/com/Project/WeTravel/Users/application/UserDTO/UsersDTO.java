
package com.Project.WeTravel.Users.application.UserDTO;

import java.util.Date;


public class UsersDTO {
    
    private String name;
    private String email;
    private String userName;
    private Long idUser;
    private String biography;
    private String photo;
    private Date creationDate;
    private Date lastLogin;
    private Date editionDate;

    
    
    public UsersDTO() {
        
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(Date editionDate) {
        this.editionDate = editionDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    
    @Override
    public String toString() {
        return "usersDTO{" + "name=" + name + 
                ", email=" + email +
                ", biography=" + biography + 
                ", photo=" + photo + 
                ", creationDate=" + creationDate +
                ", lastLogin=" + lastLogin +
                ", editionDate=" + editionDate + '}';
    }
    
    
    
    
    
    
}