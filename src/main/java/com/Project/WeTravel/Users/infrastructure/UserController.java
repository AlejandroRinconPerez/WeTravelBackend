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
import java.util.Optional;

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

    @GetMapping("/{email}")
   public ResponseEntity<UsersDTO> getUserById(@PathVariable String email) {
        Users user = userService.findUserbyEmail(email).getBody();
        return ResponseEntity.ok(user.toDTO());
    }
 
    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        return userService.createUser(createUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<UsersDTO> updateUser(
            @PathVariable String email,
            @RequestBody Users user
    ) {

        return userService.updateUserDetails(email, user);
    }

    @GetMapping("/notfolloging/{email}")
    public List<UsersDTO> getuserNotFollowing(@PathVariable String email) {
        Users user = userService.findUserbyEmail(email).getBody();
        List<UsersDTO> userlist = userService.alluserthatarenotfollowingme(email);

        return userlist;
    }

   @GetMapping("/verify/{email}/{password}")
public Boolean verifyEmail(@PathVariable String email, @PathVariable String password) {
    return userService.verificarUserEmailPassword(email, password);
}

    @PutMapping("/status/{email}")
    public UsersDTO updateUserStatus(@PathVariable String email) {

        return userService.cambiarStatus(email);
    }

    
        @GetMapping("/username/{userName}")
   public ResponseEntity<UsersDTO> getUserName(@PathVariable String userName) {
        Users user = userService.findUserbyUsername(userName).getBody();
        return ResponseEntity.ok(user.toDTO());
    }
 
    
}
