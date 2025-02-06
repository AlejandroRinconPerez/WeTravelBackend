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
import org.springframework.web.bind.annotation.PutMapping;
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
    //  post  por usuario los que yo sigo 
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
//  post por tag 

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<CombinePostDTO>> getAllPostTags(@PathVariable String tag) {
        try {
            List<CombinePostDTO> listaPost = postServiceImpl.findPostsByTagContent(tag);
            return ResponseEntity.ok(listaPost);
        } catch (Exception e) {

            e.printStackTrace();

            System.err.println("Error fetching posts by tag: " + tag);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @GetMapping("/like")
    public ResponseEntity<List<CombinePostDTO>> getAllLikes() {
        try {
            List<CombinePostDTO> listaPost = postServiceImpl.findAllOrderByLikesDesc();
            return ResponseEntity.ok(listaPost);
        } catch (Exception e) {

            e.printStackTrace();

            System.err.println("Error fetching the post with likes  ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

// Todos los post todos sin verificqr  uusrios 
    @GetMapping("post")
    public ResponseEntity<List<CombinePostDTO>> getAllL() {
        try {
            List<CombinePostDTO> listaPost = postServiceImpl.getAllPost();
            return ResponseEntity.ok(listaPost);
        } catch (Exception e) {

            e.printStackTrace();

            System.err.println("Error fetching the post with likes  ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

// Todos los post pero que el usuasrio este activado 
    @GetMapping
    public ResponseEntity<List<CombinePostDTO>> findPostsByActiveUsers() {
        try {
            List<CombinePostDTO> listaPost = postServiceImpl.findPostsByActiveUsers();
            return ResponseEntity.ok(listaPost);
        } catch (Exception e) {

            e.printStackTrace();

            System.err.println("Error fetching the post  ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

// Post de los que yo sigo 
    @GetMapping("/my/{idUser}")
    public ResponseEntity<List<CombinePostDTO>> findPostsByUserId(@PathVariable Long idUser) {
        try {
            List<CombinePostDTO> listaPost = postServiceImpl.findPostsByUserId(idUser);
            return ResponseEntity.ok(listaPost);
        } catch (Exception e) {

            e.printStackTrace();

            System.err.println("Error fetching the post  ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @GetMapping("/liked/{idUser}")
    public ResponseEntity<List<CombinePostDTO>> findPostsLikedByUserId(@PathVariable Long idUser) {
        try {
            List<CombinePostDTO> listaPost = postServiceImpl.findPostsLikedByUser(idUser);
            return ResponseEntity.ok(listaPost);
        } catch (Exception e) {

            e.printStackTrace();

            System.err.println("Error fetching the post  ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }
@PutMapping("/{postId}")
public ResponseEntity<ShowPostDTO> updatePost(
    @PathVariable Long postId,
    @RequestBody CreatePostDTO createPostDTO
) {
    Post updatedPost = postServiceImpl.updatePost(postId, createPostDTO).getBody();
    return ResponseEntity.ok(updatedPost.toShowPostDTO());
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    return postServiceImpl.deletePost(id);
}


}





