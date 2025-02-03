/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Likes.infrastructure;

import com.Project.WeTravel.Likes.application.DTO.LikeCommentDTO;
import com.Project.WeTravel.Likes.application.DTO.LikePostDTO;
import com.Project.WeTravel.Likes.application.LikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/like")
public class LikeController {
    
    
private final LikeServiceImpl likeServiceImpl;


    @Autowired
    public LikeController(LikeServiceImpl likeServiceImpl) {
        this.likeServiceImpl = likeServiceImpl;
    }


    
    
 @PostMapping ("/likepost/{idUser}/{idPost}")
 public ResponseEntity<LikePostDTO> createLikePost(@PathVariable Long idUser, @PathVariable Long idPost  ){
try {
     return likeServiceImpl.createLikePost(idPost, idUser);
} catch (Exception e){
      e.printStackTrace(); 
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
}
 }
    
    
 @PostMapping ("/likecomment/{idUser}/{idComment}")
 public ResponseEntity<LikeCommentDTO> createLikecomment(@PathVariable Long idUser, @PathVariable Long idComment  ){
try {
     return likeServiceImpl.createLikeComment(idComment, idUser);
} catch (Exception e){
      e.printStackTrace(); 
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
}
 }
    

    
    
    
    
    
}
