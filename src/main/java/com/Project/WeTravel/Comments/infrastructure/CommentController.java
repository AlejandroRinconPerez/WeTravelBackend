package com.Project.WeTravel.Comments.infrastructure;

import com.Project.WeTravel.Comments.application.CommentServicesImpl;
import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Post.application.PostServiceImpl;
import com.Project.WeTravel.Post.domain.Post;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    
    private final PostServiceImpl postServiceImpl;
    private final CommentServicesImpl commentServicesImpl;
  @Autowired
    public CommentController(PostServiceImpl postServiceImpl, CommentServicesImpl commentServicesImpl) {
        this.postServiceImpl = postServiceImpl;
        this.commentServicesImpl = commentServicesImpl;
    }

    
    
    
  
  

// Encontrar Comentario npor id 
    @GetMapping("/findId/{idComment}")
    public ResponseEntity<Comment> findComentBYid(@PathVariable Long idComment) {
        return commentServicesImpl.findComentBYid(idComment);
    }

// Create Comment 
    @PostMapping("/{email}/{idPost}")
    public ResponseEntity<CommentDTO> createComment(@PathVariable String email, @PathVariable Long idPost, @RequestBody Comment comment) {
        ResponseEntity<CommentDTO> commentsaved = commentServicesImpl.createComment(idPost, comment, email);

        return commentsaved;
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentServicesImpl.deleteComment(id);
    }

   @PutMapping("/{id}")
public ResponseEntity<CommentDTO> updateComment(@PathVariable("id") Long idComment, @RequestBody Comment commentText) {
    Comment commentToUpdate = commentServicesImpl.findComentBYid(idComment).getBody();
    if (commentToUpdate == null) {
        return ResponseEntity.noContent().build();
    }
    
    Post post = postServiceImpl.getPostbycomment( commentServicesImpl.findComentBYid(idComment).getBody());
    if (post == null) {
        return ResponseEntity.badRequest().build();
    }
    commentToUpdate.setPost(post);
    commentToUpdate.setContent(commentText.getContent());
    
    commentServicesImpl.saveComment(commentToUpdate);
    
    return ResponseEntity.ok(commentToUpdate.toDTO());
}


}
