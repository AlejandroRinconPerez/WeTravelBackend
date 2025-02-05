package com.Project.WeTravel.Post.infrastructure;

import com.Project.WeTravel.CombinePost.CombinePostDTO;
import com.Project.WeTravel.Post.application.DTO.DTO.CreatePostDTO;
import com.Project.WeTravel.Post.application.DTO.DTO.ShowPostDTO;
import com.Project.WeTravel.Post.application.PostServiceImpl;
import com.Project.WeTravel.Post.domain.Post;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostServiceImpl postServiceImpl;

    @Autowired
    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }

    @PostMapping("{idUser}")
    public ResponseEntity<Post> CreatePost(@PathVariable Long idUser, @RequestBody CreatePostDTO createPostDTO) {

        if (idUser == null) {
            return ResponseEntity.badRequest().build();
        }
        if (createPostDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        return postServiceImpl.createPost(createPostDTO, idUser);
    }
//
//          @GetMapping("/correct")
//        public List<CombinePostDTO> getAllPostWithiAll(){
//            return postServiceImpl.getAllPosts2();
//        }
//        
//   
//   @GetMapping
//    public ResponseEntity<List<ShowPostDTO>> getAllPosts() {
//        try {
//            List<ShowPostDTO> posts = postServiceImpl.getAllPosts();
//            return new ResponseEntity<>(posts, HttpStatus.OK);
//        } catch (Exception e) {
//            // Loggea el error para más detalles
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("{userId}/posts")
    public List<ShowPostDTO> getUserPosts(@PathVariable Long userId) {
        return postServiceImpl.getPostsByUserId(userId);
    }

// Post Pendiente 
    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        return postServiceImpl.deletePost(id);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<CombinePostDTO>> getAllPostsUser(@PathVariable Long idUser) {
        try {
            List<CombinePostDTO> listaPost = postServiceImpl.findByUserOrderByCreationDateDesc(idUser);
            return ResponseEntity.ok(listaPost);
        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

@GetMapping("/tag/{tag}")
    public ResponseEntity<List<CombinePostDTO>> getAllPostTags(@PathVariable String tag) {
        try {
            List<CombinePostDTO> listaPost = postServiceImpl.findPostsByTagContent(tag);
            return ResponseEntity.ok(listaPost);
        } catch (Exception e) {
            // Log the exception details
            e.printStackTrace();
            // You can also log a custom message
            System.err.println("Error fetching posts by tag: " + tag);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }}
    
    

    

}
