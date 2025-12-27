package com.gfg;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private UserIdentifier userIdentifier;

    @NotBlank
    private String identifiervalue;

    public User toUser(){
        return User.builder()
                .name(name).password(password)
                .phoneNumber(phoneNumber).email(email)
                .userIdentifier(userIdentifier).identifiervalue(identifiervalue)
                .build();
    }
}
