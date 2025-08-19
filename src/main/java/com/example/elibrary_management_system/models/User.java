package com.example.elibrary_management_system.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User implements UserDetails {

    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAutorities = new ArrayList<>();
        grantedAutorities.add(new SimpleGrantedAuthority(Authority.STUDENT.name()));
        grantedAutorities.add(new SimpleGrantedAuthority(Authority.ADMIN.name()));

        return grantedAutorities;
    }

    @OneToOne(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Student student;

    @OneToOne(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Admin admin;
}

/* Mapping
* User <-> Admin.   1:1
*
* User <-> Student. 1:1
*/

