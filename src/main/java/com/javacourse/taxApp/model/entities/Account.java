package com.javacourse.taxApp.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "account")
@Inheritance(strategy= InheritanceType.JOINED)
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    protected Long id;

    @NotEmpty(message = "msg.fields-error")
    @Pattern(regexp = "^[0-9a-zA-Z]{3,20}$",message = "msg.login-error")
    @Column(name = "login")
    protected String username;

    @ToString.Exclude
    @NotEmpty(message = "msg.fields-error")
    @Column(name = "password_hash")
    protected String password;

    @NotEmpty(message = "msg.fields-error")
    @Email(message = "msg.mail-error")
    @Column(name = "email")
    protected String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
