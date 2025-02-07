
package com.Project.WeTravel.Users.infrastructure;

import com.Project.WeTravel.Users.domain.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserJpaRepositorty extends JpaRepository<Users, Long>{


  Optional<Users> findByuserName(String name);
  Optional<Users> findByemail(String Email);
  Optional<Users> findBypassword(String password);
  
@Query("SELECT u FROM Users u " +
       "WHERE u.active = true " +  // Solo usuarios activos
       "AND u NOT IN (" +
       "    SELECT f.followed FROM Follow f WHERE f.follower = :user" +
       ")")
List<Users> findUsersNotFollowedBy(@Param("user") Users user);
}
