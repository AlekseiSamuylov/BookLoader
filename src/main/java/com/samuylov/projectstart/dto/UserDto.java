package com.samuylov.projectstart.dto;

import com.samuylov.projectstart.enumeration.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto extends AbstractDto{
    private String name;
    private String password;
    private UserRole role;
}
