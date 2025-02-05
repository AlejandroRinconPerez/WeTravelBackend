
package com.Project.WeTravel.Comments.infrastructure;

import com.Project.WeTravel.Comments.application.CommentServicesImpl;
import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/comment")
public class CommentController {
    
    
private final CommentServicesImpl commentServicesImpl;

    @Autowired
    public CommentController(CommentServicesImpl commentServicesImpl) {
        this.commentServicesImpl = commentServicesImpl;
    }
    
    
// Encontrar Comentario npor id 
    
    
    @GetMapping("/findId/{ idComment}")
    public ResponseEntity<Comment> findComentBYid(@PathVariable Long idComment){
       return commentServicesImpl.findComentBYid(idComment);
    }
    
    
    
    
    
    
// Create Comment 
    
 @PostMapping("/commet/{idUser}/{idPost}")
 public ResponseEntity<CommentDTO> createComment(@PathVariable Long idUser,@PathVariable Long idPost, @RequestBody Comment comment  ){
  ResponseEntity<CommentDTO> commentsaved = commentServicesImpl.createComment(idPost, idUser, comment);

     return commentsaved;
 }
 
 
 @DeleteMapping ("/{id}")
 public void deleteComment(@PathVariable Long id){
     commentServicesImpl.deleteComment(id);
 }
 
 @PutMapping
 
 public ResponseEntity<CommentDTO> updateComment(@PathVariable Long idComment, @RequestBody Comment commenttext){
      Comment commentToUpdate =  commentServicesImpl.findComentBYid(idComment).getBody();
      commentToUpdate.setContent(commenttext.getContent());
      CommentDTO commenttoretur= commentServicesImpl.saveComment(commenttext).getBody().toDTO();
      return   ResponseEntity.ok(commenttoretur);
     
     
 }
 
    
}
