package com.yanolja_final.domain.user.entity;

import com.yanolja_final.global.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String username;

    private String encryptedPassword;

    private String image;

    private boolean isTermsAgreed = false;

    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities;

    @Builder
    public  User(String email, String phoneNumber, String username,
        String encryptedPassword,boolean isTermsAgreed, Set<Authority> authorities) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.isTermsAgreed = isTermsAgreed;
        this.authorities = authorities;
    }

    public void updateCredentials(String username, String phoneNumber, String encryptedPassword) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public void restore() {
        super.restore();
    }

    @Override
    public void delete(LocalDateTime currentTime) {
        super.delete(currentTime);
    }
}
