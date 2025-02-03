package com.Project.WeTravel.Photo.domain;

import com.Project.WeTravel.Photo.application.DTO.PhotoDTOurl;
import com.Project.WeTravel.Post.domain.Post;
import jakarta.persistence.*;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhoto;

    @Column(name = "Url")
    private String UrlImg;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPost")
    private Post post;

    public Photo() {
    }

    public Photo(Long idPhoto, String UrlImg, Post post) {
        this.idPhoto = idPhoto;
        this.UrlImg = UrlImg;
        this.post = post;
    }

    public Long getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Long idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getUrlImg() {
        return UrlImg;
    }

    public void setUrlImg(String UrlImg) {
        this.UrlImg = UrlImg;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    
    public PhotoDTOurl toPhotoDTOurl() {
        PhotoDTOurl photoDTOurl = new PhotoDTOurl();
        photoDTOurl.setUrl(this.UrlImg);
        return photoDTOurl;
    }

    public static Photo fromPhotoDTOurl(PhotoDTOurl photoDTOurl) {
        Photo photo = new Photo();
        photo.setUrlImg(photoDTOurl.getUrl());
        return photo;
    }
    
    
    
    @Override
    public String toString() {
        return "Photo{" + "idPhoto=" + idPhoto + ", UrlImg=" + UrlImg + ", post=" + post + '}';
    }

    
    
    
    
}
