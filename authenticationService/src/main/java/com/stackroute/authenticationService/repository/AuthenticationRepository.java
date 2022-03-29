package com.stackroute.authenticationService.repository;

import com.stackroute.authenticationService.domain.UserLogIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<UserLogIn , String>
{
    UserLogIn findByEmailIdAndPassword(String emailId,String password);
}
