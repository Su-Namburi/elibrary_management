package com.example.elibrary_management_system.dtos;

import com.example.elibrary_management_system.models.Admin;
import com.example.elibrary_management_system.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String name;

    public Admin toAdmin() {

        return Admin.builder()
                .name(this.name)
                .user(User.builder()
                        .username(this.username)
                        .password(this.password)
                        .build())
                .build();
    }
}
