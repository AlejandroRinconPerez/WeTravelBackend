package com.Project.WeTravel.Users.domain;

import com.Project.WeTravel.Users.application.UserDTO.CreateUserDTO;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Folllow.domain.Follow;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Notification.domain.Notification;
import com.Project.WeTravel.Post.domain.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")           
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    private String biography;
    private String photo;

    private Boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Temporal(TemporalType.TIMESTAMP)
    private Date editionDate;

    // FOLLOWER
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Follow> followerList = new ArrayList<>();

    //FOLLOWED
    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Follow> followedList = new ArrayList<>();

    // @JsonManagedReference so el DTO  no sirve Activar
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Likes> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "toUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notifications = new ArrayList<>();

    public Users() {
        this.creationDate = new Date(); // asi se asi se asigna la fecha de creacion de manera automatica 

    }

    public Users(String name, String userName, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    // Métodos para followerList
    public List<Follow> getFollowerList() {
        return followerList;
    }

    public void addFollower(Follow follow) {
        this.followerList.add(follow);
        follow.setFollower(this);
    }

    public void removeFollower(Follow follow) {
        this.followerList.remove(follow);
        follow.setFollower(null);
    }

    // Métodos para followedList
    public List<Follow> getFollowedList() {
        return followedList;
    }

    public void addFollowed(Follow follow) {
        this.followedList.add(follow);
        follow.setFollowed(this);
    }

    public void removeFollowed(Follow follow) {
        this.followedList.remove(follow);
        follow.setFollowed(null);
    }

    // Métodos para postList
    public List<Post> getPostList() {
        return postList;
    }

    public void addPost(Post post) {
        this.postList.add(post);
        post.setUser(this);
    }

    public void removePost(Post post) {
        this.postList.remove(post);
        post.setUser(null);
    }

    // Métodos para likeList
    public List<Likes> getLikeList() {
        return likeList;
    }

    public void addLike(Likes like) {
        this.likeList.add(like);
        like.setUser(this);
    }

    public void removeLike(Likes like) {
        this.likeList.remove(like);
        like.setUser(null);
    }

    // Métodos para commentList
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
        comment.setUser(this);
    }

    public void removeComment(Comment comment) {
        this.commentList.remove(comment);
        comment.setUser(null);
    }
// Notificaton
    public List<Notification> getNotifications() {
        return notifications;
    }

    public void addNotification(Notification notification) {
        this.notifications.add(notification);
        notification.setToUser(this);
    }

    public void removeNotification(Notification notification) {
        this.notifications.remove(notification);
        notification.setToUser(null);
    }

    public UsersDTO toDTO() {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setBiography(this.biography);
        usersDTO.setCreationDate(this.creationDate);
        usersDTO.setEditionDate(this.editionDate);
        usersDTO.setName(this.name);
        usersDTO.setEmail(this.email);
        usersDTO.setPhoto(this.photo);
        usersDTO.setUserName(this.getUserName());

        usersDTO.setLastLogin(this.lastLogin);
        return usersDTO;

    }

    public static Users fromDTO(UsersDTO usersDTO) {
        Users user = new Users();
        user.setBiography(usersDTO.getBiography());
        user.setCreationDate(usersDTO.getCreationDate());
        user.setEditionDate(usersDTO.getEditionDate());
        user.setEmail(usersDTO.getEmail());
        user.setName(usersDTO.getName());
        user.setLastLogin(usersDTO.getLastLogin());
        user.setPhoto(usersDTO.getPhoto());
        user.setUserName(usersDTO.getUserName());
        return user;
    }

    public CreateUserDTO toDTOCreate() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setName(this.name);
        createUserDTO.setEmail(this.email);
        createUserDTO.setPassword(this.password);
        createUserDTO.setUserName(this.userName);
        return createUserDTO;

    }

    public static Users fromDTOCreate(CreateUserDTO createUserDTO) {
        Users user = new Users();
        user.setName(createUserDTO.getName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        user.setUserName(createUserDTO.getUserName());
        return user;
    }

    @Override
    public String toString() {
        return "Users{" + "idUser=" + idUser
                + ", name=" + name
                + ", userName=" + userName
                + ", email=" + email
                + ", password=" + password
                + ", biography=" + biography
                + ", photo=" + photo
                + ", active=" + active
                + ", creationDate=" + creationDate
                + ", lastLogin=" + lastLogin
                + ", editionDate=" + editionDate
                + ", followerList=" + followerList
                + ", followedList=" + followedList
                + ", postList=" + postList
                + ", likeList=" + likeList
                + ", commentList=" + commentList + '}';
    }

}
