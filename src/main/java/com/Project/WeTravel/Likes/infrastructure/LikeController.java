/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Likes.infrastructure;

import com.Project.WeTravel.Likes.application.DTO.LikeCommentDTO;
import com.Project.WeTravel.Likes.application.DTO.LikePostDTO;
import com.Project.WeTravel.Likes.application.LikeServiceImpl;
import com.Project.WeTravel.Utilities.exceptions.InvalidInputException;
import com.Project.WeTravel.Utilities.exceptions.NotFoundException;
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

    @PostMapping("/likepost/{email}/{idPost}")
    public ResponseEntity<LikePostDTO> createLikePost(@PathVariable String email, @PathVariable Long idPost) {
        try {
            return likeServiceImpl.createLikePost(idPost, email);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/likecomment/{idUser}/{idComment}")
    public ResponseEntity<LikeCommentDTO> createLikecomment(@PathVariable String email, @PathVariable Long idComment) {
        try {
            return likeServiceImpl.createLikeComment(idComment, email);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

 @DeleteMapping("{idLike}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long idLike) {
        try {
            likeServiceImpl.deleteLike(idLike);
            return ResponseEntity.ok().build();
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}