package com.samuylov.projectstart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCredentials {

    private String username;
    private String password;

    public UserCredentials(final UserCredentials credentials) {
        if (credentials != null) {
            this.password = credentials.getPassword();
            this.username = credentials.getUsername();
        }
    }
}
