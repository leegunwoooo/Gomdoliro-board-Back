package com.example.human2.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@Getter
@Entity
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
        return null; // 권한을 따로 관리하지 않는 경우 null 또는 빈 리스트 반환
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
