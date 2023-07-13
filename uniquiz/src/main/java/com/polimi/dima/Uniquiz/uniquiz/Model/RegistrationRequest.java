package com.polimi.dima.Uniquiz.uniquiz.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest{
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String universityName;
}
