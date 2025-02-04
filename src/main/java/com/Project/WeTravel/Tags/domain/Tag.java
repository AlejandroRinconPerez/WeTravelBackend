package com.Project.WeTravel.Tags.domain;

import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Tags.application.TagsDTO.TagDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTag;

   private String tagContent; 

    
    
    @ManyToMany
    @JoinTable(
            name = "Post_Tag", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "tag_id"), // Clave foránea en la tabla intermedia
            inverseJoinColumns = @JoinColumn(name = "post_id") // Clave foránea de la otra entidad
    
    )
    @JsonIgnore
    private List<Post> postList = new ArrayList<>();

    public Tag() {
    }

    
    
    public Tag(String tagContent) {
        this.tagContent = tagContent;
    }    
    
    
   
    public Tag(Long idTag, String tagContent) {
        this.idTag = idTag;
        this.tagContent = tagContent;
    }

    public Long getIdTag() {
        return idTag;
    }

    public void setIdTag(Long idTag) {
        this.idTag = idTag;
    }

    public String getTagContent() {
        return tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }

    public List<Post> getPostList() {
        return postList;
    }

   
    public void addPost(Post post) {
        if (post != null) {
            this.postList.add(post); // Agrega el Post a la lista de Tags
            post.getTagList().add(this); // Agrega este Tag a la lista de Posts
        }
    }

 
    public void removePost(Post post) {
        if (post != null) {
            this.postList.remove(post); // Elimina el Post de la lista de Tags
            post.getTagList().remove(this); // Elimina este Tag de la lista de Posts
        }

    }

    
  
    
    
    @Override
    public String toString() {
        return "Tag{" + "idTag=" + idTag + 
                ", tagContent=" + tagContent +
                ", postList=" + postList + '}';
    }

    
    
    
    
}
