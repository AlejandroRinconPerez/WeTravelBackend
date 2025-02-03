
package com.Project.WeTravel.Users.infrastructure;

import com.Project.WeTravel.Users.domain.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepositorty extends JpaRepository<Users, Long>{


  Optional<Users> findByuserName(String name);
  Optional<Users> findByemail(String Email);
  Optional<Users> findBypassword(String password);
  
    
}
