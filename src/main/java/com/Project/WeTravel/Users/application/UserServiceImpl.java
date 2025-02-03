package com.Project.WeTravel.Users.application;

import com.Project.WeTravel.Users.application.UserService;
import com.Project.WeTravel.Users.application.UserDTO.CreateUserDTO;
import com.Project.WeTravel.Users.domain.Users;
import com.Project.WeTravel.Users.application.UserDTO.UsersDTO;
import com.Project.WeTravel.Users.infrastructure.UserJpaRepositorty;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepositorty userJpaRepositorty;

    @Autowired
    public UserServiceImpl(UserJpaRepositorty userJpaRepositorty) {
        this.userJpaRepositorty = userJpaRepositorty;
    }

    @Override

    public List<UsersDTO> getAllUsers() {
        List<Users> users = userJpaRepositorty.findAll();
        return users.stream()
                .map(Users::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<Users> getAllUsersNormal() {
        List<Users> users = userJpaRepositorty.findAll();
        return users;

    }

    @Override
    public Users getUserNormalbyId(Long userid){
        Users user =  userJpaRepositorty.findById(userid).get();
        return user;
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
        return usersOpt.map(users -> ResponseEntity.ok(users.toDTO()))
                .orElse(ResponseEntity.notFound().build());
    }



    @Override
    public ResponseEntity<UsersDTO> createUser(CreateUserDTO createUserDTO) {
        Users usercreated = Users.fromDTOCreate(createUserDTO);
        usercreated = userJpaRepositorty.save(usercreated);
        return ResponseEntity.ok(usercreated.toDTO());

    }

    @Override
    public ResponseEntity<UsersDTO> saveUser(Users user) {
        Users userdaved = userJpaRepositorty.save(user);
        return ResponseEntity.ok(userdaved.toDTO());
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long idUser) {
        if (userJpaRepositorty.existsById(idUser)) {
            userJpaRepositorty.deleteById(idUser);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
