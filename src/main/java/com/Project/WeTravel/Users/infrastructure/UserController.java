package com.Project.WeTravel.Users.infrastructure;

import com.Project.WeTravel.Users.application.UserServiceImpl;
import com.Project.WeTravel.Users.application.UserService;
import com.Project.WeTravel.Users.application.UserDTO.CreateUserDTO;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UsersDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/update/{idUser}")
    public ResponseEntity<UsersDTO> updateUser(
            @PathVariable Long idUser,
            @RequestBody Users user
    ) {

        Users userToUpdate = userService.getUserNormalbyId(idUser);

        if (userToUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        if (user.getName() != null) {
            userToUpdate.setName(user.getName());
        }
        if (user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }
        if (user.getBiography() != null) {
            userToUpdate.setBiography(user.getBiography());
        }
        if (user.getPhoto() != null) {
            userToUpdate.setPhoto(user.getPhoto());
        }
        if (user.getActive() != null) {
            userToUpdate.setActive(user.getActive());
        }
        userToUpdate.setEditionDate(new Date());
        UsersDTO responseDTO = userService.saveUser(userToUpdate).getBody();
        return ResponseEntity.ok(responseDTO);
    }

}
