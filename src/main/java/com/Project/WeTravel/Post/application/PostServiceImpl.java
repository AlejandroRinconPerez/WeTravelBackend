/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Post.application;

import com.Project.WeTravel.Photo.application.PhotoServiceImpl;
import com.Project.WeTravel.CombinePost.CombinePostDTO;
import com.Project.WeTravel.Post.application.DTO.DTO.CreatePostDTO;
import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Post.infrastructure.PostJpaRepository;
import com.Project.WeTravel.Tags.application.TagServiceIpml;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import com.Project.WeTravel.Users.application.UserServiceImpl;
import com.Project.WeTravel.Users.domain.Users;
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
   

    @Autowired
    public PostServiceImpl(PostJpaRepository postJpaRepository, UserServiceImpl userServiceImpl) {
        this.postJpaRepository = postJpaRepository;
        this.userServiceImpl = userServiceImpl;
     
    }

    @Override
    public Post createPost(CreatePostDTO createPostDTO) {

        Post newPost = Post.fromDTO(createPostDTO);
        newPost = postJpaRepository.save(newPost);

        // Por ahora esto es un metodo basico de Post sin foto sin likes sin comentarios 
        return newPost;

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
    public  List<ShowPostDTO> getAllPosts() {

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
         Post postid =  postJpaRepository.findById(postId).get();
        return postid;
                
    }
 
    
}
