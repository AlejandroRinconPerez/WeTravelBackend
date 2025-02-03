
package com.Project.WeTravel.Comments.infrastructure;

import com.Project.WeTravel.Comments.application.CommentServicesImpl;
import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    
    
    
// Create Comment 
    
 @PostMapping("/commet/{idUser}/{idPost}")
 public ResponseEntity<CommentDTO> createComment(@PathVariable Long idUser,@PathVariable Long idPost, @RequestBody Comment comment  ){
  ResponseEntity<CommentDTO> commentsaved = commentServicesImpl.createComment(idPost, idUser, comment);

     return commentsaved;
 }
 
 
    
}
