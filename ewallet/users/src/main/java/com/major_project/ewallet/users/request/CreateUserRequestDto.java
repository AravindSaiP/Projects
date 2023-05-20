package com.major_project.ewallet.users.request;

import com.major_project.ewallet.users.entity.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequestDto {
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String contactNumber;
    private String dob;

    public UserInfo toUserInfo(){
        return UserInfo.builder()
                .email(email)
                .name(name)
                .contactNumber(contactNumber)
                .dob(dob)
                .build();
    }

}
