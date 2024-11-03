package com.example.human2.domain.User;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String nickName;

    public String getUseremail() {
        return email;
    }

    public String getUsername(){
        return nickName;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Builder
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void setNickname(String nickName) {
        this.nickName = nickName;
    }
}
