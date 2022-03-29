package com.stackroute.authenticationService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserLogIn
{
    @Id
    @Column(name = "emailId", nullable = false)
    private String emailId;
    private String password;
    private Role role;
}
