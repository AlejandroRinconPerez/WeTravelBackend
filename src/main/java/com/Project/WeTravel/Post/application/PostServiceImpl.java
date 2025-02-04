/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Post.application;

import com.Project.WeTravel.Photo.application.PhotoServiceImpl;
import com.Project.WeTravel.CombinePost.CombinePostDTO;
import com.Project.WeTravel.Photo.domain.Photo;
import com.Project.WeTravel.Photo.infrastructure.PhotoJpaRepository;
import com.Project.WeTravel.Post.application.DTO.DTO.CreatePostDTO;
import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Post.infrastructure.PostJpaRepository;
import com.Project.WeTravel.Tags.application.TagServiceIpml;
import com.Project.WeTravel.Tags.domain.Tag;
import com.Project.WeTravel.Tags.infrastructure.TagJpaRepository;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import com.Project.WeTravel.Users.application.UserServiceImpl;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Utilities.exceptions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostJpaRepository postJpaRepository;
    private final UserServiceImpl userServiceImpl;
    private final PhotoJpaRepository photoJpaRepository;
    private final TagJpaRepository tagJpaRepository;

    @Autowired
    public PostServiceImpl(PostJpaRepository postJpaRepository, UserServiceImpl userServiceImpl, PhotoJpaRepository photoJpaRepository, TagJpaRepository tagJpaRepository) {
        this.postJpaRepository = postJpaRepository;
        this.userServiceImpl = userServiceImpl;
        this.photoJpaRepository = photoJpaRepository;
        this.tagJpaRepository = tagJpaRepository;
    }

    @Override
    public ResponseEntity<Post> createPost(CreatePostDTO createPostDTO, Long iduser) {
        if (iduser == null) {
            throw new InvalidInputException("User Id invalid");
        }
        List<Photo> photoList = new ArrayList();
        List<Tag> tagList = new ArrayList();
        
        
        Users user = userServiceImpl.getUserNormalbyId(iduser);
        if (user == null) {
            throw new NotFoundException("Users has not been found");
        }
        Post post = new Post();
        post.setDescription(createPostDTO.getDescription());
        post.setCreationDate(createPostDTO.getCreationDate());
        post.setUpdatedDate(createPostDTO.getUpdatedDate());
        post.setUser(user);

        if (createPostDTO.getListPhoto() != null && !createPostDTO.getListPhoto().isEmpty()) {
            for (String phototext : createPostDTO.getListPhoto()) {
                Photo photo = new Photo(phototext);
                photo.setPost(post);
                photoJpaRepository.save(photo);
                post.getPhotolist().add(photo);
            }
        }

        if (createPostDTO.getListTag() != null && !createPostDTO.getListTag().isEmpty()) {
            for (String tagtext : createPostDTO.getListTag()) {
                 Tag tag = new Tag(tagtext);
                if(!tagJpaRepository.existsByTagContent(tagtext)){
                  tagJpaRepository.save(tag); // lo persistimos
                } else {
                    tag = tagJpaRepository.findBytagContent(tagtext).get();
                }
                  post.getTagList().add(tag);
                  tag.getPostList().add(post);
           
            }
        }

        return ResponseEntity.ok(postJpaRepository.save(post));

    }

    @Override
    public ResponseEntity<Void> deletePost(Long idPost) {

        if (postJpaRepository.existsById(idPost)) {
            postJpaRepository.deleteById(idPost);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public List<ShowPostDTO> getAllPosts() {

        List<Post> postList = postJpaRepository.findAll();
        return postList.stream()
                .map(Post::toShowPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowPostDTO> getPostsByUserId(Long userId) {
        Users user = userServiceImpl.getUserNormalbyId(userId);

        List<Post> postList = postJpaRepository.findByuser(user);
        return postList.stream()
                .map(Post::toShowPostDTO)
                .collect(Collectors.toList());
    }

    public List<Post> getPostsByUserIdNoraml(Long userId) {
        Users user = userServiceImpl.getUserNormalbyId(userId);
        List<Post> postList = postJpaRepository.findByuser(user);
        return postList;

    }

    public Post getPostsByPostid(Long postId) {
        Post postid = postJpaRepository.findById(postId).get();
        return postid;

    }

}
