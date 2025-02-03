package com.Project.WeTravel.Post.domain;

import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Photo.domain.Photo;
import com.Project.WeTravel.Post.application.DTO.DTO.CreatePostDTO;
import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import com.Project.WeTravel.Tags.domain.Tag;
import com.Project.WeTravel.Users.domain.Users;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", nullable = false)
    //@JsonBackReference // si el DTO  no sirve Activar 
    private Users user;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date creationDate;

    private Date updatedDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Photo> photolist;

    @ManyToMany(mappedBy = "postList")
    private List<Tag> tagList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Likes> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    public Post() {
    }

    public Post(Long idPost, Users user, String description, Date creationDate, Date updatedDate, List<Photo> postList) {
        this.idPost = idPost;
        this.user = user;
        this.description = description;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
        this.photolist = postList;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void addTag(Tag tag) {
        if (tag != null) {
            this.tagList.add(tag);
            tag.getPostList().add(this);
        }
    }

    public void removeTag(Tag tag) {
        if (tag != null) {
            this.tagList.remove(tag);
            tag.getPostList().remove(this);

        }
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
        comment.setPost(this);

    }

    public void removeComment(Comment comment) {
        this.commentList.remove(comment);
        comment.setPost(null);
    }

    public List<Likes> getLikeList() {
        return likeList;
    }

    public void addLike(Likes like) {
        this.likeList.add(like);
        like.setPost(this);

    }

    public void removeLike(Likes like) {
        this.likeList.remove(like);
        like.setPost(null);
    }

    public List<Photo> getPhotolist() {
        return photolist;
    }

    public void addPhoto(Photo photo) {
        this.photolist.add(photo);
        photo.setPost(this);
    }

    public void removePhoto(Photo photo) {
        this.photolist.remove(photo);
        photo.setPost(null);

    }

    public CreatePostDTO toDTO() {
        CreatePostDTO createPostDTO = new CreatePostDTO();
        createPostDTO.setUser(this.user);
        createPostDTO.setDescription(this.description);
        return createPostDTO;
    }

    public static Post fromDTO(CreatePostDTO createPostDTO) {
        Post post = new Post();
        post.setUser(createPostDTO.getUser());
        post.setDescription(createPostDTO.getDescription());
        post.setCreationDate(createPostDTO.getCreationDate());
        post.setUpdatedDate(createPostDTO.getUpdatedDate());
        return post;
    }

public ShowPostDTO toShowPostDTO() {
    ShowPostDTO showPostDTO = new ShowPostDTO();
    showPostDTO.setPostid(this.idPost); 
    showPostDTO.setUser(this.user.toDTO()); 
    showPostDTO.setDescription(this.description);
    showPostDTO.setCreationDate(this.creationDate);
    showPostDTO.setUpdatedDate(this.updatedDate);

    return showPostDTO;
}

public static Post fromDTO(ShowPostDTO showPostDTO) {
    Post post = new Post();
    post.setIdPost(showPostDTO.getPostid()); 
    Users user = Users.fromDTO(showPostDTO.getUser()); 
    post.setUser(user);
    post.setDescription(showPostDTO.getDescription());
    post.setCreationDate(showPostDTO.getCreationDate());
    post.setUpdatedDate(showPostDTO.getUpdatedDate());

    return post;
}




    @Override
    public String toString() {
        return "Post{" + "idPost=" + idPost
                + ", user=" + user
                + ", description=" + description
                + ", creationDate=" + creationDate
                + ", updatedDate=" + updatedDate
                + ", postList=" + photolist
                + ", tagList=" + tagList
                + ", likeList=" + likeList
                + ", commentList=" + commentList + '}';

    }

}
