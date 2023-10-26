package org.example.springbootdeveloper.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String userId;
    private String password;
    private String email;
    private String nickname;
    private char gender;
    private Boolean status;
}
