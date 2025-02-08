
package com.Project.WeTravel.Users.application;

import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import com.Project.WeTravel.Users.application.UserDTO.CreateUserDTO;
import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface UserService {
    
    // Obtener todos los usuarios
    List<UsersDTO> getAllUsers();
    
    // Obtener un usuario por su ID
    ResponseEntity<UsersDTO> getUserById(Long idUser);
    
    // Crear un nuevo usuario
    ResponseEntity<UsersDTO> createUser(CreateUserDTO createUserDTO);
    
    // Eliminar un usuario dado su ID
    ResponseEntity<Void> deleteUser(Long idUser);
    
    // Verificar el nombre de usuario, contraseña y correo electrónico
    Boolean verificarUsername(String username, String password, String email);
    
    // Verificar el nombre de usuario y contraseña
    Boolean verificarUsername(String username, String password);
    
    // Obtener un usuario normal por su ID
    Users getUserNormalbyId(Long iduser);
    
    // Guardar un usuario
    ResponseEntity<UsersDTO> saveUser(Users user);
    
    // Obtener todos los usuarios normales
    List<Users> getAllUsersNormal();

    // Encontrar un usuario por su nombre de usuario
    ResponseEntity<Users> findUserbyUsername(String email);

    // Encontrar un usuario por su correo electrónico
    ResponseEntity<Users> findUserbyEmail(String email);

    // Actualizar los detalles de un usuario
    ResponseEntity<UsersDTO> updateUserDetails(String email, Users user);

    // Obtener todos los usuarios que no me siguen
    List<UsersDTO> alluserthatarenotfollowingme(String email);

    // Verificar el correo electrónico y la contraseña de un usuario
    Boolean verificarUserEmailPassword(String email, String password);
    
    // Cambiar el estado de un usuario
    UsersDTO cambiarStatus(String email);
}
