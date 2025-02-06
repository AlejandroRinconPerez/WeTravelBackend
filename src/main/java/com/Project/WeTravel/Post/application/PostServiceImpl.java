/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Post.application;

import com.Project.WeTravel.Photo.application.PhotoServiceImpl;
import com.Project.WeTravel.CombinePost.CombinePostDTO;
import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Likes.application.DTO.LikePostDTO;
import com.Project.WeTravel.Likes.domain.Likes;
import com.Project.WeTravel.Photo.application.DTO.PhotoDTOurl;
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
import java.util.Date;
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
                if (!tagJpaRepository.existsByTagContent(tagtext)) {
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
    Optional<Post> postOpt = postJpaRepository.findById(idPost);
    if (postOpt.isPresent()) {
        Post post = postOpt.get();

     
        List<Tag> tags = post.getTagList();
        for (Tag tag : tags) {
            tag.getPostList().remove(post);
            tagJpaRepository.save(tag);
        }
        post.getTagList().clear();
        postJpaRepository.save(post);

      
        postJpaRepository.delete(post);
        return ResponseEntity.noContent().build(); 
    } else {
        return ResponseEntity.notFound().build(); 
    }
}


    public void createPost(Post post) {
        postJpaRepository.save(post);
    }


    @Override
    public List<ShowPostDTO> getAllPosts() {

        List<Post> postList = postJpaRepository.findAll();
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

    // METODOS NUEVOS CON FILTRO DE USUARIOS ACTIVOS EN GENERAL 
    // Posts por usuario activo ordenados por fecha
    // ya esta
    public List<CombinePostDTO> findByUserOrderByCreationDateDesc(Long idUser) {
        Users user = userServiceImpl.getUserNormalbyId(idUser);
        List<Post> postList = postJpaRepository.findByUserOrderByCreationDateDesc(user);
        return getAllPosts2(postList);
    }

    public List<CombinePostDTO> getAllPost() {
        List<Post> postList = postJpaRepository.findAll();
        return getAllPosts2(postList);
    }

    //Posts por tag (usuario activo) ordenados por fecha
    // ya etsa 
    public List<CombinePostDTO> findPostsByTagContent(String tagtext) {

        List<Post> postList = postJpaRepository.findPostsByTagContent(tagtext);
        return getAllPosts2(postList);
    }
    // Posts en rango de fechas (usuario activo) ordenados por fecha
//este no se usara 

    // Todos los posts (usuarios activos) ordenados por likes
    public List<CombinePostDTO> findAllOrderByLikesDesc() {
        List<Post> postList = postJpaRepository.findAllOrderByLikesDesc();
        return getAllPosts2(postList);
    }

    public List<CombinePostDTO> findPostsByUserId(Long userid) {
        List<Post> postList = postJpaRepository.findPostsByUserId(userid);
        return getAllPosts2(postList);
    }

    public List<CombinePostDTO> findPostsByActiveUsers() {
        List<Post> postList = postJpaRepository.findPostsByActiveUsers();
        return getAllPosts2(postList);
    }

    public List<CombinePostDTO> findPostsLikedByUser(Long id) {
        List<Post> postList = postJpaRepository.findLikedPostsByUserId(id);
        return getAllPosts2(postList);
    }

    // Encontrar post by comment 
    public Post getPostbycomment(Comment comment) {
        return postJpaRepository.findByCommentInList(comment);

    }

    public ResponseEntity<Post> updatePost(Long postId, CreatePostDTO createPostDTO) {
        Optional<Post> postOpt = postJpaRepository.findById(postId);
        if (!postOpt.isPresent()) {
            throw new NotFoundException("Post no encontrado");
        }

        Post post = postOpt.get();

        // Actualizar campos básicos
        if (createPostDTO.getDescription() != null) {
            post.setDescription(createPostDTO.getDescription());
        }
        post.setUpdatedDate(new Date()); // Actualizar fecha de modificación

        // Manejo de fotos (eliminar existentes y agregar nuevas)
        if (createPostDTO.getListPhoto() != null) {
            // Eliminar fotos antiguas
            photoJpaRepository.deleteAll(post.getPhotolist());
            post.getPhotolist().clear();

            // Agregar nuevas fotos
            for (String photoUrl : createPostDTO.getListPhoto()) {
                Photo photo = new Photo(photoUrl);
                photo.setPost(post);
                photoJpaRepository.save(photo);
                post.getPhotolist().add(photo);
            }
        }

        // Manejo de tags
        if (createPostDTO.getListTag() != null) {
            post.getTagList().clear();
            for (String tagContent : createPostDTO.getListTag()) {
                Tag tag = tagJpaRepository.findBytagContent(tagContent)
                        .orElseGet(() -> tagJpaRepository.save(new Tag(tagContent)));
                post.getTagList().add(tag);
            }
        }

        return ResponseEntity.ok(postJpaRepository.save(post));
    }

//  esta funcion sirve para convertir a cualquier lista de post en un post DTO  
    public List<CombinePostDTO> getAllPosts2(List<Post> postList) {
        // Lista Final Para el REtur
        List<CombinePostDTO> combinePost = new ArrayList();
        for (Post postItem : postList) {

            CombinePostDTO combinePostFinal = new CombinePostDTO();
            List< PhotoDTOurl> photoDTOurl = new ArrayList();
            List< LikePostDTO> likePostDTO = new ArrayList();
            List< CommentDTO> commentDTO = new ArrayList();
            List<Tag> taglist = new ArrayList();
            // Photo
            List<Photo> photoList = postItem.getPhotolist();

            for (Photo photoItem : photoList) {
                PhotoDTOurl phototosaveDTO = photoItem.toPhotoDTOurl();
                photoDTOurl.add(phototosaveDTO);
            }
            // showPostDTO
            ShowPostDTO showPostDTO = postItem.toShowPostDTO();
            // Tag 
            taglist = postItem.getTagList();
            // Like
            for (Likes likeitem : postItem.getLikeList()) {

                likePostDTO.add(likeitem.toLikePostDTO());
            }
            // Comment
            for (Comment commentItem : postItem.getCommentList()) {
                commentDTO.add(commentItem.toDTO());
            }
            combinePostFinal.setTagDTO(taglist);
            combinePostFinal.setPhotoDTOurl(photoDTOurl);
            combinePostFinal.setCommentDTO(commentDTO);
            combinePostFinal.setLikePostDTO(likePostDTO);
            combinePostFinal.setShowPostDTO(showPostDTO);
            combinePost.add(combinePostFinal);
        }
        return combinePost;
    }

    @Override
    public List<ShowPostDTO> getPostsByUserId(Long idUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ResponseEntity<Post> getPostByIdLike(Long idLike) {
        System.out.println("Lo estoy buscando");
        Post post = postJpaRepository.findPostByLikeId(idLike);
        System.out.println(post);
        if (post == null) {
            System.out.println("No se encontró el Post para el likeId: " + idLike);
            return ResponseEntity.notFound().build();
        }
        System.out.println("Post encontrado: " + post);
        return ResponseEntity.ok(post);

    }

}
