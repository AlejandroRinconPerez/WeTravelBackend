package com.Project.WeTravel.Users.application;

import com.Project.WeTravel.Users.application.UserService;
import com.Project.WeTravel.Users.application.UserDTO.CreateUserDTO;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import com.Project.WeTravel.Users.infrastructure.UserJpaRepositorty;
import com.Project.WeTravel.Utilities.exceptions.InvalidInputException;
import com.Project.WeTravel.Utilities.exceptions.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepositorty userJpaRepositorty;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserJpaRepositorty userJpaRepositorty, PasswordEncoder passwordEncoder) {
        this.userJpaRepositorty = userJpaRepositorty;
        this.passwordEncoder = passwordEncoder;
    }

    @Override

    public List<UsersDTO> getAllUsers() {
        List<Users> users = userJpaRepositorty.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("Users has not been foud");
        }
        return users.stream()
                .map(Users::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<Users> getAllUsersNormal() {
        List<Users> users = userJpaRepositorty.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("Users has not been foud");
        }
        return users;

    }

    @Override
    public Users getUserNormalbyId(Long userid) {
        Optional< Users> user = userJpaRepositorty.findById(userid);
        if (user.isEmpty()) {
            throw new NotFoundException("User has not been foud");
        }
        return user.get();
    }

    @Override
    public Boolean verificarUsername(String username, String password, String email) {
        Optional<Users> userOpt = userJpaRepositorty.findByuserName(username);

        Optional<Users> userOpt2 = userJpaRepositorty.findByemail(email);

        return userOpt.isPresent() && userOpt2.isPresent() && userOpt.get().getPassword().equals(password);
    }

    @Override
    public Boolean verificarUsername(String username, String password) {
        Optional<Users> userOpt = userJpaRepositorty.findByuserName(username);

        return userOpt.isPresent() && userOpt.get().getPassword().equals(password);
    }

    @Override
    public ResponseEntity<UsersDTO> getUserById(Long idUser) {

        Optional<Users> usersOpt = userJpaRepositorty.findById(idUser);

        if (usersOpt.isEmpty()) {
            throw new NotFoundException("User has not been foud");
        }
        return usersOpt.map(users -> ResponseEntity.ok(users.toDTO()))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UsersDTO> createUser(CreateUserDTO createUserDTO) {
        if (createUserDTO == null) {
            throw new NotFoundException("No info in the body try again");
        }
        if (createUserDTO.getUserName() == null || createUserDTO.getUserName().trim().isEmpty()) {
            throw new InvalidInputException("user name is mandatory");
        }

        if (createUserDTO.getEmail() == null || createUserDTO.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email is needed");
        }
        Users usercreated = Users.fromDTOCreate(createUserDTO);
        usercreated.setActive(true);
        usercreated.setCreationDate(new Date());
        
        String encryptedPassword = passwordEncoder.encode(createUserDTO.getPassword());
        
        usercreated.setPassword(encryptedPassword);

        usercreated = userJpaRepositorty.save(usercreated);
        return ResponseEntity.ok(usercreated.toDTO());

    }

    @Override
    public ResponseEntity<UsersDTO> saveUser(Users user) {
        if (user == null) {
            throw new InvalidInputException("El objeto usuario no puede ser nulo");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("El email es requerido");
        }

        Users userdaved = userJpaRepositorty.save(user);
        return ResponseEntity.ok(userdaved.toDTO());
    }

    public Users saveUserEntity(Users user) {
        user.setLastLogin(new Date());
        return userJpaRepositorty.save(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long idUser) {
        if (idUser == null || idUser <= 0) {
            throw new InvalidInputException("El ID de usuario no es válido");

        }
        if (!userJpaRepositorty.existsById(idUser)) {
            throw new NotFoundException("Usuario no encontrado con ID: " + idUser);
        }

        if (userJpaRepositorty.existsById(idUser)) {
            userJpaRepositorty.deleteById(idUser);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<Users> findUserbyUsername(String email) {

        Users user = userJpaRepositorty.findByuserName(email).get();
        if (user == null) {
            throw new NotFoundException("User NotFound");
        }

        UsersDTO userDTO = user.toDTO();
        return ResponseEntity.ok(user);

    }

    @Override
    public ResponseEntity<Users> findUserbyEmail(String email) {

        Users user = userJpaRepositorty.findByemail(email).get();
        if (user == null) {
            throw new NotFoundException("User NotFound");
        }

        UsersDTO userDTO = user.toDTO();
        return ResponseEntity.ok(user);

    }

    @Override
    public ResponseEntity<UsersDTO> updateUserDetails(String email, Users user) {
        Users userToUpdate = findUserbyEmail(email).getBody();

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

        UsersDTO responseDTO = saveUser(userToUpdate).getBody();
        return ResponseEntity.ok(responseDTO);
    }

    @Override
    public List<UsersDTO> alluserthatarenotfollowingme(String emial) {

        Users user = userJpaRepositorty.findByemail(emial).get();

        List<Users> useerList = userJpaRepositorty.findUsersNotFollowedBy(user);
        if (useerList.isEmpty()) {
            throw new NotFoundException("Users has not been foud");
        }
        return useerList.stream()
                .map(Users::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public Boolean verificarUserEmailPassword(String email, String password) {

        Optional<Users> userEmailOptional = userJpaRepositorty.findByemail(email);
        Optional<Users> userPasswordOptional = userJpaRepositorty.findBypassword(password);

        if (userEmailOptional.isPresent() && userPasswordOptional.isPresent()) {
            Users userEmail = userEmailOptional.get();
            Users userPassword = userPasswordOptional.get();
            return userEmail.getIdUser().equals(userPassword.getIdUser());
        } else {
            return false;
        }
    }

    @Override
    public UsersDTO cambiarStatus(String email) {

        Users user = userJpaRepositorty.findByemail(email).get();
        user.setActive(false);
        user = userJpaRepositorty.save(user);
        return user.toDTO();

    }
public Boolean verificarLogin(String username, String password) {
    Optional<Users> userOpt = userJpaRepositorty.findByUserName(username);
    
    if (userOpt.isPresent()) {
        Users user = userOpt.get();
        return passwordEncoder.matches(password, user.getPassword());
    }
    return false;
}

}
