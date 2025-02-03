/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Project.WeTravel.Photo.application;

import com.Project.WeTravel.Photo.application.DTO.PhotoDTOurl;
import com.Project.WeTravel.Post.domain.Post;
import com.Project.WeTravel.Users.application.UserDTO.CreateUserDTO;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;


public interface PhotoService {
    
    
//    List<UsersDTO> getAllUsers();
//    ResponseEntity<UsersDTO>getUserById(Long idUser);
//    ResponseEntity<UsersDTO>createUser(CreateUserDTO createUserDTO);
//    ResponseEntity<Void>deleteUser(Long idUser);
//    Boolean verificarUsername(String username, String password);
//    Boolean verificarUsername(String username);
//            
//    
//    ResponseEntity<UsersDTO>createUser(CreateUserDTO createUserDTO);
    
    
    List<PhotoDTOurl> findByPost(Post Post);
    
    
    
    
    
}
