
package com.Project.WeTravel.Security;


import com.Project.WeTravel.Users.application.UserDTO.CreateUserDTO;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import com.Project.WeTravel.Users.application.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoginController {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UserServiceImpl userService;

    

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam("userName") String username,
            @RequestParam("password") String password) {

        if (userService.verificarUsername(username, password)) {
            String token = jwtAuthtenticationConfig.getJWTToken(username);

            LoginUser user = new LoginUser(username, token);
             
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");

    }

    
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDTO createUserDTO) {
        try {
            if(userService.verificarUsername(createUserDTO.getUserName(), createUserDTO.getPassword(), createUserDTO.getEmail())){
                return ResponseEntity.badRequest().body("User already exits.");
            }

            ResponseEntity<UsersDTO> savedUser = userService.createUser(createUserDTO);

            return ResponseEntity.ok("Usuario registrado exitosamente: " + savedUser.getBody().getName());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el usuario.");
        }
    }

    
    
}
