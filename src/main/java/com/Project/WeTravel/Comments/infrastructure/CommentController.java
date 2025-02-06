package com.Project.WeTravel.Comments.infrastructure;

import com.Project.WeTravel.Comments.application.CommentServicesImpl;
import com.Project.WeTravel.Comments.application.DTO.CommentDTO;
import com.Project.WeTravel.Comments.domain.Comment;
import com.Project.WeTravel.Utilities.exceptions.NotFoundException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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

    private final CommentServicesImpl commentServicesImpl;

    @Autowired
    public CommentController(CommentServicesImpl commentServicesImpl) {
        this.commentServicesImpl = commentServicesImpl;
    }

// Encontrar Comentario npor id 
    @GetMapping("/findId/{idComment}")
    public ResponseEntity<Comment> findComentBYid(@PathVariable Long idComment) {
        return commentServicesImpl.findComentBYid(idComment);
    }

// Create Comment 
    @PostMapping("/commet/{email}/{idPost}")
    public ResponseEntity<CommentDTO> createComment(@PathVariable String email, @PathVariable Long idPost, @RequestBody Comment comment) {
        ResponseEntity<CommentDTO> commentsaved = commentServicesImpl.createComment(idPost, comment, email);

        return commentsaved;
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentServicesImpl.deleteComment(id);
    }

    @PatchMapping
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long idComment, @RequestBody Comment commenttext) {
        Comment commentToUpdate = commentServicesImpl.findComentBYid(idComment).getBody();
        if (commentToUpdate == null) {
            return ResponseEntity.noContent().build();

        }

        commentToUpdate.setContent(commenttext.getContent());
        commentToUpdate.setUpDatedAt(new Date());
        CommentDTO commenttoretur = commentServicesImpl.saveComment(commenttext).getBody().toDTO();
        
       
        return ResponseEntity.ok(commenttoretur);

    }

    
    
}
